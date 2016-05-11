package messagingAplicationPackage.Application;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    private String serverIp;
    private Socket connection;
    private String message, response;
    private BufferedReader bufinput = null;
    private PrintWriter out = null;

    public Client(String host) {
        serverIp = host;
    }

    //connect to server
    public void startRunning() {
        try {
            connectToserver();
            processMessages();

        } catch (EOFException eofException) {
            System.out.println("\n Client ended the connection! ");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            closeConnections();
        }

    }

    //connect to server
    private void connectToserver() throws IOException {
        System.out.println("Attempting connection... \n");
        connection = new Socket(InetAddress.getByName(serverIp), 9999);
        System.out.println("Connected to:" + connection.getInetAddress().getHostName());
    }

    //read and send messages to the server
    private void processMessages() {
        try {

            out = new PrintWriter(connection.getOutputStream(), true);
            bufinput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Scanner scInput = new Scanner(System.in);
            message = "";
            do {
                if (!message.equalsIgnoreCase("end")) {
                    try {
                        response = bufinput.readLine();
                    } catch (IOException ioException) {
                        System.out.println("I dont Know what the hell the server respond back!!!");
                    }
                    try {
                        
                        while (bufinput.ready()) {
                            response += "\n" + bufinput.readLine();
                        }
                        
                    } catch (IOException ioException) {
                        System.out.println("I dont Know what the hell the server respond back!!!");
                    }
                    System.out.println("From server: " + response);
                    message = scInput.nextLine();
                } else {
                    System.out.println("You requested session to end...");
                }
            } while (!message.equalsIgnoreCase("end"));
        } catch (IOException ioException) {
            System.out.println("Error setting up the streams in the Client!!!");
        }

    }

    private void closeConnections() {
        System.out.println(" Closing connections... \n");
        try {
            out.close();
            bufinput.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client("localhost");
        c.startRunning();
    }
}
