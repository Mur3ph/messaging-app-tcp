package our.email.app;

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

import our.email.classes.Message;
import our.email.classes.User;

public class Provider implements Runnable
{

    private Socket         link;
    private List<String>   messages = null;
    private BufferedReader in       = null;
    private PrintWriter    out      = null;
    private User           user;

    public Provider(Socket socket)
    {
        link = socket;
    }

    public void run()
    {
        System.out.println("Now interacting with the client..");

        try
        {
            in = new BufferedReader(
                    new InputStreamReader(link.getInputStream()));
            out = new PrintWriter(link.getOutputStream(), true);

            String input = in.readLine();
            String action = "login";

            String username, recipient = null;

            while (!input.equalsIgnoreCase("/exit"))
            {
                if (action.equals("login"))
                {
                    out.println("Type your username to start");

                    if (!Database.exists(input))
                    {
                        Database.newUser(input);
                    }

                    user = new User(input);

                    action = "menu";
                } else if (action.equals("menu"))
                {
                    out.println("Type 'send' to send a message. Type 'view' to view your messages:");
                    input = in.readLine();
                    
                    if (input.equalsIgnoreCase("send")) {
                        action = "send";
                    } else if (input.equalsIgnoreCase("view")) {
                        action = "view";
                    } else {
                        out.print("Invalid selection");
                    }
                } else
                {
                    out.println("Error: action not defined. Logging out");
                    action = "login";
                }
            }

            while (!input.equalsIgnoreCase("exit"))
            {
                if (action.equalsIgnoreCase("send"))
                {
                    if (recipient == null)
                    {
                        // Get recipient...
                        out.println("Who do you want to send your message to???");
                        // System.out.println(user.getName() + "> ");
                        input = in.readLine();
                        recipient = input;
                        user2 = new User(recipient);
                        // output.println("Recipient: " + user2.getName());
                    } else
                    {
                        // Recipient stored...Now, get message.
                        out.println("What is the message?");
                        input = in.readLine();

                        Database.sendMessage(recipient, input);
                        mess = new Message(input, user, user2);
                        // output.println("Message: " + mess.getMessage());

                        // Start again...
                        recipient = null;
                        action = "";
                    }
                    // Problem here, gets stuck here at this
                    // point.......................................................................................
                } else if (action.equalsIgnoreCase("view"))
                {
                    messages = Database.getMessages(username);

                    if (messages.size() > 0)
                    {
                        for (String message : messages)
                        {
                            out.print("'" + message + "'\n");
                        }
                    } else
                    {
                        out.print("You have no messages.\n");
                    }

                    action = "";
                } else
                { // no valid action selected
                    mess = new Message(input, user, user2);
                    // output.println("Welcome " + user.getName());

                }

            }

        } catch (IOException e)
        {
            System.out.println("Connection to client lost");
        } finally
        {
            try
            {
                System.out.println("Closing connection...");
                in.close();
                out.close();
                link.close();
            } catch (IOException e)
            {
                System.out.println("Unable to disconnect..");
                System.exit(1);
            }
        }
    } // End of Thread inherited method run()..........

}
