package com.murph.client;

import java.util.Vector;

public interface MyCallbackInterface extends java.rmi.Remote
{

    public Vector<String> getMessages(String username)throws java.rmi.RemoteException;
    
}
