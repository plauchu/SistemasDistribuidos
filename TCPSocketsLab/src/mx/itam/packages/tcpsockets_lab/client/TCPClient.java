package mx.itam.packages.tcpsockets_lab.client;

import java.net.*;
import java.io.*;

public class TCPClient {

    private int id;
    private int nRequests = 0;
    private int nClients = 0;

    public TCPClient(int id, int nRequests, int nClients) {
        this.id = id;
        this.nRequests = nRequests;
        this.nClients = nClients;
    }

    public void askServer() {

        Socket socket = null;
        try {
            long totalElapsedTimeMilli = 0;
            long elapsedTimes[] = new long[nRequests];
            int n = 0;

            int serverPort = 49152;

            socket = new Socket("localhost", serverPort);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (n < nRequests) {
                int key = (int) Math.round(Math.random() * 4);
                long startTimeMillis = System.currentTimeMillis();
                out.writeInt(key);
                //System.out.println("Id: "+id +"  I'm looking for " + key);
                String record = in.readUTF();
                long elapsedTimeMilli = (System.currentTimeMillis() - startTimeMillis);
                totalElapsedTimeMilli += elapsedTimeMilli;
                elapsedTimes[n] = elapsedTimeMilli;
                n++;
                //System.out.println("I got it from the server!, Record: "+ record);
            }
            out.writeInt(8); // sends exit code
            //System.out.println("Average server's response time in milliseconds : " + (double)(totalElapsedTimeMilli)/nRequests +" \n"                        + "Standard deviation of response time: " + stdDev(elapsedTimes));
            System.out.println(nClients + ","
                    + nRequests
                    + ",Avg. response  time (ms)," + (double) (totalElapsedTimeMilli) / nRequests
                    + ",Std dev of response time," + stdDev(elapsedTimes));


        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (socket != null) try {
                socket.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }

    }

    private double stdDev(long[] list) {
        double sum = 0.0;
        double num = 0.0;

        for (int i = 0; i < list.length; i++)
            sum += list[i];

        double mean = sum / list.length;

        for (int i = 0; i < list.length; i++)
            num += Math.pow((list[i] - mean), 2);

        return Math.sqrt(num / list.length);
    }
}
