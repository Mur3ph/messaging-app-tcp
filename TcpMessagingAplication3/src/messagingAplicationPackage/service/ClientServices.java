package messagingAplicationPackage.service;

import messagingAplicationPackage.database.Database;
import java.util.LinkedList;
import messagingAplicationPackage.classes.Message;
import messagingAplicationPackage.classes.User;

public class ClientServices {

    private User user;
    private Message message;
    private Database database;
    private LinkedList<Message> llMessages;

    public ClientServices() {
        user = null;
        message = null;
        database = null;
        llMessages = null;
    }

    public void addUser(String username) {
        user = new User(username);
    }

    public void sendMessage(String username, String[] recipients, String mes) {
        for (int i = 0; i < recipients.length - 1; i++) {
            message = new Message(mes, username, recipients[i]);
            //add the message to the database
            database.addMessage(message);
        }
    }

    public LinkedList<Message> ViewMessage(String username) {
        database = new Database();
        llMessages = database.getMessages(username);
        return llMessages;
    }

    public void logOut() {
        user = null;
    }
}
