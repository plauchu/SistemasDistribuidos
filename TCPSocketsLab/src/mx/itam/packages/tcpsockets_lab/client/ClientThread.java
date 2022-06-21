package mx.itam.packages.tcpsockets_lab.client;

class ClientThread extends Thread {

    private int id;
    private int nRequests;
    private int nClients;

    public ClientThread(int id, int nRequests, int nClients) {
        this.id = id;
        this.nRequests = nRequests;
        this.nClients = nClients;
    }

    @Override
    public void run() {
        try {
            TCPClient client = new TCPClient(id, nRequests, nClients);
            client.askServer();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}