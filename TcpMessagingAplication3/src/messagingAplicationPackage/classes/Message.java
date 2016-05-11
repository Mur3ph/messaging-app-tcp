package messagingAplicationPackage.classes;

public class Message {

    private String message, from, to;

    public Message() {
        message = null;
        from = null;
        to = null;
    }

    public Message(String message, String from, String to) {
        this.message = message;
        this.from = from;
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
