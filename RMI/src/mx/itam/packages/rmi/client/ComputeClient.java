package mx.itam.packages.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mx.itam.packages.rmi.interfaces.Compute;

public class ComputeClient {

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmi/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost";
            String serviceName = "Compute";
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]
            Compute comp = (Compute) registry.lookup(serviceName);

            System.out.println("3^2 = " + comp.square(3));
            System.out.println("3^3 = " + comp.power(3, 3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
