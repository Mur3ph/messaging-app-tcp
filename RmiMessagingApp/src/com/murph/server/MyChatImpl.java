package com.murph.server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.murph.client.MyCallbackInterface;
import com.murph.objects.Message;

@SuppressWarnings("serial")
public class MyChatImpl extends UnicastRemoteObject implements MyChatInterface
{

    public Map<String, Vector<String>> map = new HashMap<>();
    private Vector<MyCallbackInterface> clientList;
    

    public MyChatImpl() throws RemoteException
    {
        super();
        clientList = new Vector<MyCallbackInterface>();
    }
    
    
    public String showMenu() throws java.rmi.RemoteException
    {
        String menu = "\n " +
        		"Choose a number option from the list below:\n " +
        		"1. View your messages. \n " +
        		"2. Send a message. \n" +
        		"3. Type 'exit' to quit.\n";
        return menu;
//        System.out.println(menu); 
    }
    

    public void newUser(String username)throws java.rmi.RemoteException
    {
        map.put(username, new Vector<String>());
    }
    
    public boolean exists(String username)throws java.rmi.RemoteException
    {
        return map.get(username) != null;
    }
    
    public void sendMessage(String recipient, String message)throws java.rmi.RemoteException
    {
        Message mess = new Message(message, recipient);
        Vector<String> messages = null;
        messages = map.get(recipient);

        if (map.containsKey(recipient))
        {
            messages = map.get(recipient);
//            messages.clear();
            messages.add(message);
        }
        
    }
    
    public synchronized Vector<String> getMessages(String username)throws java.rmi.RemoteException
    {
              return map.get(username);
    }
    
    public void setMap(Map<String, Vector<String>> map)
    {
        this.map = map;
    }
    
    public Map<String, Vector<String>> getMap()
    {
        return map;
    }
    
    public synchronized void registerForCallback(MyCallbackInterface clientObject)throws java.rmi.RemoteException
    {
        
        // If the list doesn't alresdy have the client object, store the client object
        if(!(clientList.contains(clientObject)))
        {
            clientList.addElement(clientObject);
            System.out.println("Registered new client. ");
            doCallbacks();
        }
        
    }
    
    public synchronized void unregisterForCallback(MyCallbackInterface clientObject)throws java.rmi.RemoteException
    {
        
        if(clientList.removeElement(clientObject))
        {
            System.out.println("Unregistered client.");
        }
        else
        {
            System.out.println("Client wasn't registerded. ");
        }
        
    }


    private void doCallbacks()throws java.rmi.RemoteException
    {
        System.out.println("Callback initiated..................");
        
        for(int x = 0; x < clientList.size(); x++)
        {
            System.out.println("Doing " + x + "-th callback\n");
            // Convert the vector object to the callback object.
            MyCallbackInterface nextClient = clientList.elementAt(x);
            //Invoke the callback method to view the users messages.
            System.out.println(nextClient.getMessages("Number: " + clientList.size()));
        }
        
    }
    
    
 
}
