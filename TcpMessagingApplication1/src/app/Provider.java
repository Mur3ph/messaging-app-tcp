package app;

/**
 * 
 * @author Paul Murphy
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import database.Message;
import database.User;

public class Provider implements Runnable {
    private Socket link;
    private User user;

    public Provider(Socket socket) {
	link = socket;
    }

    public void run() {
	System.out.println("Now interacting with the client..");

	try {
	    BufferedReader in = new BufferedReader(new InputStreamReader(
		    link.getInputStream()));
	    PrintWriter out = new PrintWriter(link.getOutputStream(), true);

	    String input = "", action = "login";

	    while (!input.equalsIgnoreCase("exit")) {
		if (action.equals("login")) {
		    out.println("Type your username to start");
		    input = in.readLine();

		    if (!User.exists(input)) {
			User.newUser(input);
		    }

		    user = User.getUser(input);

		    action = "menu";
		} else if (action.equals("menu")) {
		    out.println(user.getName()
			    + ": Type 'send' to send a message, 'view' to view your messages, or 'logout' to log out:");
		    input = in.readLine();

		    if (input.equalsIgnoreCase("send")) {
			action = "send";
		    } else if (input.equalsIgnoreCase("view")) {
			action = "view";
		    } else if (input.equalsIgnoreCase("logout")) {
			action = "login";
		    } else {
			out.print("Invalid selection");
		    }
		} else if (action.equals("send")) {
		    out.println("Enter a recipient");

		    User recipient = User.getUser(in.readLine());

		    if (recipient == null) {
			out.print("This user does not exist. Try again\n");
		    } else {
			out.println("Enter a message");
			String content = in.readLine();

			recipient.sendMessage(content, user);
			action = "menu";
		    }
		} else if (action.equals("view")) {
		    List<Message> messages = user.getMessages();

		    if (messages.size() > 0) {
			for (Message message : messages) {
			    out.print(message.getSender().getName()
				    + ": " + message.getContent() + "\n");
			}

			messages.clear();
			out.flush();
		    } else {
			out.println("No messages");
		    }

		    action = "menu";
		} else {
		    out.println("Error: action not defined. Restarting");
		    action = "login";
		}
	    }
	} catch (IOException e) {
	    System.out.println("Connection to client lost");
	} finally {
	    try {
		System.out.println("Closing connection");
		link.close();
	    } catch (IOException e) {
		System.out.println("Unable to disconnect");
		System.exit(1);
	    }
	}
    } // End of Thread inherited method run()..........
}
