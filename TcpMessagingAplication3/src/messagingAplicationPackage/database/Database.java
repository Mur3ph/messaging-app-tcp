package messagingAplicationPackage.database;

import java.util.LinkedList;
import messagingAplicationPackage.classes.Message;

public class Database {

    private LinkedList<Message> llHoldEveryThing;
    private LinkedList<Message> llTemp;
    private LinkedList<Message> llReturned;

    public Database() {
        llHoldEveryThing = new LinkedList<Message>();
        llTemp = new LinkedList<Message>();
        llReturned = new LinkedList<Message>();
    }

    public void addMessage(Message message) {
        llHoldEveryThing.add(message);
    }

    public LinkedList<Message> getMessages(String username) {

        for (int i = 0; i < llHoldEveryThing.size(); i++) {
            if (llHoldEveryThing.get(i).getTo().equalsIgnoreCase(username)) {
                llTemp.add(llHoldEveryThing.get(i));
            }
        }
        llReturned = llTemp;
        llTemp.clear();
        return llReturned;
    }

    public void deleteMessages(String username) {
        for (int i = 0; i < llHoldEveryThing.size(); i++) {
            if (llHoldEveryThing.get(i).getTo().equalsIgnoreCase(username)) {
                llHoldEveryThing.remove(i);
                i--;
            }
        }
    }
}
