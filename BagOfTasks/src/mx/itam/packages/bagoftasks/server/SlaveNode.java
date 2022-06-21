package mx.itam.packages.bagoftasks.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import mx.itam.packages.bagoftasks.interfaces.Bioinformatics;
import mx.itam.packages.bagoftasks.interfaces.DataMining;
import mx.itam.packages.bagoftasks.interfaces.ImageProcessing;
import mx.itam.packages.bagoftasks.serializableobjects.Task;

public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {

    public SlaveNode() throws RemoteException {
        super();
    }

    public void deploy(String serviceName) {

        System.setProperty("java.security.policy", "src/mx/itam/packages/bagoftasks/server/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            SlaveNode engine = new SlaveNode();

            if (serviceName.equals("Bioinformatics")) {
                Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(serviceName, stub);
                System.out.println("Bioinformatics Engine bound");
            } else if (serviceName.equals("DataMining")) {
                DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(serviceName, stub);
                System.out.println("Data Mining Engine bound");
            } else if (serviceName.equals("ImageProcessing")) {
                ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(serviceName, stub);
                System.out.println("Image Processing Engine bound");
            }

        } catch (Exception e) {
            System.err.println("ComputeEngine exception");
            e.printStackTrace();
        }
    }

    @Override
    public Task executeBioinformaticsTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        task.setOutput("BioinformaticsOutput");
        return task;
    }

    @Override
    public Task executeDataMiningTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        task.setOutput("DataMiningOutput");
        return task;
    }

    @Override
    public Task executeImageProcessingTask(Task task) throws RemoteException {
        try {
            Thread.sleep(task.getLength());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        task.setOutput("ImageProcessingOutput");
        return task;
    }

}