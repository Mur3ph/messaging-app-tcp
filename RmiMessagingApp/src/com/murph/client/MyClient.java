package com.murph.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Vector;

import com.murph.server.MyChatInterface;

public class MyClient
{
    
    public static void main(String[] args)
    {

    @SuppressWarnings("unused")
    int RMIPort;
    String  hostName, portNum, registryURL;
    boolean done = false;
//    User user = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Vector<String> messages = null;
    
    
    try
    {
        
        System.out.println("Enter hostname: ");
        hostName = reader.readLine();
        
        System.out.println("Enter port number: ");
        portNum = reader.readLine();
        RMIPort = Integer.parseInt(portNum);
        
        registryURL = "rmi://" + hostName + ":"  + portNum + "/Chat";
        
        System.out.println("Client - Enter your username to begin.");
        MyChatInterface serverChat = null;
        
        try
        {
            serverChat = (MyChatInterface) Naming.lookup(registryURL);
        } catch (NotBoundException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        String username = reader.readLine();
        serverChat.newUser(username);
        
        System.out.println("Welcome, " + username);
        System.out.println("Type menu for options: ");
 
        while(!done)
        {
           String menu = reader.readLine();
           
           if(menu.equalsIgnoreCase("exit") || menu == null)
           {
               done = false;
               System.exit(0);
           }
           else
           {
               try
               {
                   
                   if(menu.equalsIgnoreCase("menu"))
                   {
                       System.out.println(serverChat.showMenu());
                       
                       String action = reader.readLine();
                       if(action.equalsIgnoreCase("send"))
                       {
                           System.out.println("Who would you like to send a message!?");
                           String recipient = reader.readLine();
                           if(serverChat.exists(recipient))
                           {
                               System.out.println("Whats the message, sir!?");
                               String message = reader.readLine();
                               serverChat.sendMessage(recipient, message);
                           }
                           else
                           {
                               System.out.println("This person does not exist, sir!?");
                           }
                           
                       }
                       else if(action.equalsIgnoreCase("view"))
                       {
                           //Problem with Callback here.............................................
                           MyCallbackInterface callback =  new MyCallbackImpl();
                           serverChat.registerForCallback(callback);
                           String name = reader.readLine();
                           callback.getMessages(name);
                           System.out.println("Ya know.." + callback.getMessages(name));
                           System.out.println("Ya dont know.." + callback.getMessages(username));
                           System.out.println("Ya defo dont know.." + serverChat.getMessages(username));
                           System.out.println("Or do ya.." + serverChat.getMessages(name));
                           System.out.println("Registered for callback");
                           try
                           {
                               Thread.sleep(1000);
                           }
                           catch(InterruptedException e)
                           {
                               serverChat.unregisterForCallback(callback);
                           }
                           serverChat.unregisterForCallback(callback);
                       }
                       else if(action.equalsIgnoreCase("exit"))
                       {
                               done = false;
                               System.exit(0);
                       }
                       else
                       {
                           System.out.println("Command unknown..!!!");
                       }
                       
                   }
                   
               }
               catch(Exception e)
               {
                   System.out.println("Error: innit.." + e);
               }
           }
        }
        
    }
    catch(IOException e)
    {
        System.out.println("Uh oh, Houston, we have a problem. " + e);
    }
    
    } // End of main()
    
}
