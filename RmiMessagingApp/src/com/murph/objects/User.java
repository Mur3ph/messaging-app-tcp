package com.murph.objects;

import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

public class User
{
    private String name;
    // CopyOnWriteArrayList is slow due to copying, an alternative to synchronized List - TODO Vector perhaps or Collection.sync...
    private Vector<Message> messages = new Vector<Message>();

    // The Set does not allow duplicates...
    private static Set<User> users = new CopyOnWriteArraySet<>();

    public User(String name) 
    {
	  this.name = name;
    }

    public String getName() 
    {
	  return name;
    }

    public void setName(String name) 
    {
	  this.name = name;
    }

    public List<Message> getMessages() 
    {
	  return messages;
    }

//    public void sendMessage(String content, User sender) 
//    {
//	  messages.add(new Message(content, sender));
//    }

    public static void newUser(String name) 
    {
	  users.add(new User(name));
    }

    public static boolean exists(String name) 
    {
	  return getUser(name) != null;
    }

    public static User getUser(String name)  
    {
	
        for (User user : users) 
        {
    	    if (user.getName().equals(name)) 
    	    {
    		  return user;
    	    }
	}

	return null;
    }
}