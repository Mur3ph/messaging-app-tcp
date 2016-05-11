package com.murph.objects;

public class Message {
    private final String content;
    private final String sender;
    
    
    public Message(String c, String s) 
    {
	  content = c;
	  sender = s;
    }

    public String getSender() 
    {
	  return sender;
    }

    public String getContent() 
    {
	  return content;
    }
}
