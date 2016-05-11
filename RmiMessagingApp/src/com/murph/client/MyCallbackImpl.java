package com.murph.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.murph.objects.Message;
import com.murph.server.MyChatImpl;

public class MyCallbackImpl extends UnicastRemoteObject implements MyCallbackInterface
{

    MyChatImpl my = new MyChatImpl();
    Message mess = null;
    public Map<String, Vector<String>> map = new HashMap<>();
    String one, two;
    
    public MyCallbackImpl(String o, String t)throws RemoteException
    {
        one = o;
        two = t;
    }
    
    public MyCallbackImpl() throws RemoteException
    {
        super();
    }

    public synchronized Vector<String> getMessages(String username)throws java.rmi.RemoteException
    {
        username=mess.getSender();
       return map.get(username);
    }
    
    public Vector<String> getName(String username)
    {
        return my.map.get(username);
    }
    
}
