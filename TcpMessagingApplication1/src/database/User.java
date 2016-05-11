package database;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class User {
    private String name;
    private List<Message> messages = new CopyOnWriteArrayList<>();

    // Set does not allow duplicates
    private static Set<User> users = new CopyOnWriteArraySet<>();

    public User(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Message> getMessages() {
	return messages;
    }

    public void sendMessage(String content, User sender) {
	messages.add(new Message(content, sender));
    }

    public static void newUser(String name) {
	users.add(new User(name));
    }

    public static boolean exists(String name) {
	return getUser(name) != null;
    }

    public static User getUser(String name) {
	for (User user : users) {
	    if (user.getName().equals(name)) {
		return user;
	    }
	}

	return null;
    }
}