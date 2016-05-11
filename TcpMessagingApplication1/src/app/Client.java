package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author Paul Murphy
 */

public class Client {
    private final int PORT = 2012;
    private BufferedReader in;
    private PrintWriter out;
    private Socket link;

    public static void main(String[] args) {
	new Client();
    }

    private Client() {
	try {
	    link = new Socket("localhost", PORT);

	    in = new BufferedReader(
		    new InputStreamReader(link.getInputStream()));
	    out = new PrintWriter(link.getOutputStream(), true);

	    Scanner scanner = new Scanner(System.in);

	    String message;

	    do {
		// From server
		String input = in.readLine();
		while (in.ready()) {
		    input += "\n" + in.readLine();
		}
		System.out.println(input);
		
		// To server
		message = scanner.nextLine();
		out.println(message);
	    } while (!message.equalsIgnoreCase("exit"));

	    scanner.close();

	    System.out.println("You requested session to end.");
	} catch (IOException e) {
	    System.out.println("Connection to server lost.");
	} finally {
	    closeConnection();
	}

	System.out.println("Ending chat.");
    } // End of my Run method

    private void closeConnection() {
	System.out.println("Closing connection.");

	try {
	    if (in != null)
		in.close();

	    if (out != null)
		out.close();

	    if (link != null)
		link.close();

	    System.out.println("Closed...");
	} catch (IOException e) {
	    System.out.println("Unable to disconnect..");
	    System.exit(1);
	}
    } // End of close connection method...
} // End of class.....
