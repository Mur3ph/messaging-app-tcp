package com.murph.server;

import java.util.Vector;

import com.murph.client.MyCallbackInterface;

public interface MyChatInterface extends java.rmi.Remote
{

    public String showMenu() throws java.rmi.RemoteException;
    public void newUser(String username)throws java.rmi.RemoteException;
    public boolean exists(String username)throws java.rmi.RemoteException;
    public void sendMessage(String recipient, String message)throws java.rmi.RemoteException;
    
    public Vector<String> getMessages(String username)throws java.rmi.RemoteException;
    
    public void registerForCallback(MyCallbackInterface clientObject)throws java.rmi.RemoteException;
    public void unregisterForCallback(MyCallbackInterface clientObject)throws java.rmi.RemoteException;
}
