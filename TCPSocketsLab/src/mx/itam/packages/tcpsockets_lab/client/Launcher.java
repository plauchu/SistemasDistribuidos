package mx.itam.packages.tcpsockets_lab.client;

public class Launcher {

    private static final int nClients = 300; // 1, 10, 20, ..., 100, 150, 200, 250, 300
    private static final int nRequests = 2000; // 1000 y 2000

    public static void main(String args[]) throws InterruptedException {

        for (int i = 0; i < nClients; i++) {
            ClientThread clientThread = new ClientThread(i, nRequests, nClients);
            clientThread.start();
        }

    }
}


