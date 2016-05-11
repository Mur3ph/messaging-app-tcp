package our.email.classes;

public class Message {

	private String content;
	private User sender;
	private User recipient;
	
	public Message(){
	    content = null;
		sender = null;
		recipient = null;
	}

	public Message(String message, User s, User r) {
		content = message;
		sender = s;
		recipient = r;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getMessagecontent() {
		return content;
	}

	public void setMessagecontent(String content) {
		this.content = content;
	}
	
	
	
}
