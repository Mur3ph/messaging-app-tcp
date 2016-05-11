package database;

public class Message {
    private final String content;
    private final User sender;

    public Message(String content, User sender) {
	this.content = content;
	this.sender = sender;
    }

    public User getSender() {
	return sender;
    }

    public String getContent() {
	return content;
    }
}
