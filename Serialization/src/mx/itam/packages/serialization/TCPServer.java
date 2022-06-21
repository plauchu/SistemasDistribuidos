package mx.itam.packages.serialization;

/**
 * @author Octavio Gutierrez
 */

import java.net.*;
import java.io.*;


public class TCPServer {

    public static void main(String args[]) {
        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();  // Listens for connections.
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}

class Connection extends Thread {
    ObjectOutputStream out;
    ObjectInputStream in;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            Person me = (Person) in.readObject();
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            out.writeObject(me);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
} 
