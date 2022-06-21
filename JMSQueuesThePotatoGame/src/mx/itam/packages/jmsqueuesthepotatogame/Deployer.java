package mx.itam.packages.jmsqueuesthepotatogame;

public class Deployer {

    public static void main(String[] args) {
        Player player1 = new Player("queue1", "queue2", "potatoA");
        Player player2 = new Player("queue2", "queue1", "potatoB");
        player1.start();
        player2.start();
    }
}
