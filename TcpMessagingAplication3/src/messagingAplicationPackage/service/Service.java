package messagingAplicationPackage.service;

import messagingAplicationPackage.service.ClientServices;
import messagingAplicationPackage.database.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.ListIterator;
import messagingAplicationPackage.userInterface.CmdInterface;
import messagingAplicationPackage.classes.Message;
import messagingAplicationPackage.classes.User;

public class Service implements Runnable {

    private Socket connection;
    private BufferedReader in;
    private PrintWriter out;
    private ClientServices clientServices;
    private CmdInterface cmdInterface;
    private String username, userInput, recipient, message;
    private String[] recipients;
    private LinkedList<Message> messagesReceived;
    private ListIterator<Message> iter;
    private User user;
    private Message messageRecived;
    private Database database;

    public Service(Socket link) {
        connection = link;
        in = null;
        out = null;
        clientServices = null;
        cmdInterface = null;
        username = null;
        userInput = null;
        recipient = null;
        message = null;
        recipients = null;
        messagesReceived = null;
        iter = null;
        user = null;
        messageRecived = null;
        database = null;
    }

    //Now interacting with the client
    public void run() {
        // do some Initializations
        try {

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream(), true);

            cmdInterface = new CmdInterface();
            clientServices = new ClientServices();
            user = new User();
            database = new Database();
            userInput = "";

            do {
                // start Interacting!
                out.println(cmdInterface.printInstructions());
                username = in.readLine();
                System.out.println("im Here !!!!!!!!!");
                if (username == null || username.equals("")) {
                    out.println(cmdInterface.printInvalidUsername());
                    username = in.readLine();
                    System.out.println("im Here !!!!!!!!! Again");
                } else if (username.equalsIgnoreCase("end")) {
                    out.println(cmdInterface.printExit());
                } else {
                    clientServices.addUser(username);
                    out.println(cmdInterface.printWelcomeUser(user.getName()));
                    out.println(cmdInterface.printOptions());
                    userInput = in.readLine();

                    secoundWhile:
                    while (!userInput.equalsIgnoreCase("end")) {

                        if (userInput.equalsIgnoreCase("1")) {
                            out.println(cmdInterface.printRecipientNames());
                            recipient = in.readLine();
                            recipients = recipient.split(", ");
                            out.println(cmdInterface.printEnterMessage());
                            message = in.readLine();
                            clientServices.sendMessage(username, recipients, message);
                            out.println(cmdInterface.printMessageSent());
                            out.println(cmdInterface.printOptions());
                            userInput = in.readLine();
                        } else if (userInput.equalsIgnoreCase("2")) {
                            messagesReceived = clientServices.ViewMessage(username);
                            if (messagesReceived.isEmpty()) {
                                out.println(cmdInterface.printNoMessages());
                                out.println(cmdInterface.printOptions());
                                userInput = in.readLine();
                            } else {
                                iter = (ListIterator<Message>) messagesReceived.iterator();
                                while (iter.hasNext()) {
                                    messageRecived = iter.next();
                                    out.println(cmdInterface.printViewMessage(messageRecived));
                                }
                                out.println(cmdInterface.printOptions());
                                userInput = in.readLine();
                                database.deleteMessages(user.getName());
                            }
                        } else if (userInput.equalsIgnoreCase("3")) {
                            out.println(cmdInterface.printLogOutMessage());
                            clientServices.logOut();
                            //break from the secound while// userInput = "end";
                            break secoundWhile;
                        } else {
                            out.println(cmdInterface.printInvalidChoice());
                            out.println(cmdInterface.printOptions());

                            userInput = in.readLine();

                        }
                    }
                }
            } while (!userInput.equalsIgnoreCase("end"));
            out.println(cmdInterface.printExit());
        } catch (IOException ioException) {
            System.out.println("Error setting up input/output streams in the server side");
        } finally {
            try {
                System.out.println("Closing connection...");
                out.close();
                in.close();
                connection.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect..");
                System.exit(1);
            }
        }

    }
}
