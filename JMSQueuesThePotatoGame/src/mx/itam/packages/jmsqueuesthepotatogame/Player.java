package mx.itam.packages.jmsqueuesthepotatogame;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

public class Player extends Thread {

    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String inputQueue;
    private String outputQueue;
    private String potatoId;


    public Player(String inputQueue, String outputQueue, String potatoId) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
        this.potatoId = potatoId;
    }

    @Override
    public void run() {

        MessageProducer messageProducer;
        MessageConsumer messageConsumer;
        ObjectMessage objectMessage;

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination1 = session.createQueue(inputQueue);
            Destination destination2 = session.createQueue(outputQueue);
            messageConsumer = session.createConsumer(destination1);
            messageProducer = session.createProducer(destination2);


            objectMessage = session.createObjectMessage();
            Random random = new Random();
            Potato myPotato = new Potato(potatoId, random.nextInt(10) + 1);

            System.out.println("Sending for the very first time my potato " + myPotato.getId());

            objectMessage.setObject(myPotato);
            messageProducer.send(objectMessage);

            boolean stillOnTheAir = true;
            while (stillOnTheAir) {
                Potato potato;
                System.out.println("Waiting for potatoes...");
                potato = (Potato) ((ObjectMessage) messageConsumer.receive()).getObject();
                System.out.print("I got potato " + potato.getId() + " from my neighbor ");
                System.out.println();
                if (potato.isDropped()) {
                    System.out.println("I lost! >( I dropped potato " + potato.getId());
                    stillOnTheAir = false;
                } else {
                    potato.decreaseRemainingTime();
                    System.out.println("Sending back potato " + potato.getId());
                    objectMessage = session.createObjectMessage();
                    objectMessage.setObject(potato);
                    messageProducer.send(objectMessage);
                }

            }

            messageProducer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
