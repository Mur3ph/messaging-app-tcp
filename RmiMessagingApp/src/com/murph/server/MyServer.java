package com.murph.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class MyServer
{

    public static void main(String[] args)
    {
        String portNum, registryURL;
        int RMIPortNum;
//        boolean done = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try
        {
                System.out.println("Enter port number: ");
                portNum = reader.readLine().trim();
                RMIPortNum = Integer.parseInt(portNum);
                
                startRegistry(RMIPortNum);
                
                registryURL = "rmi://localhost:" + RMIPortNum + "/Chat";
                MyChatImpl serverImpl = new MyChatImpl();
                Naming.rebind(registryURL, serverImpl);
                
                System.out.println("Server ready");
                
//                while(!done)
//                {
//                    
//                }
            
        }
        catch(Exception e)
        {
            System.out.println("Error " + e);
        }
        
    }

    
    
    private static void startRegistry(int RMIPortNum) throws RemoteException
    {
        try
        {
           LocateRegistry.createRegistry(RMIPortNum);
           Registry registry = LocateRegistry.getRegistry(RMIPortNum);
           registry.list();
        }
        catch(RemoteException e)
        {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            System.out.println("What the..." + registry);
        }
    }
    
    
    
}
