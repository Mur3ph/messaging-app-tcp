package our.email.app;

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

public class Client
{
    private final int      PORT      = 2012;
    private BufferedReader bufinput  = null;
    private PrintWriter    output    = null;
    private Socket         link      = null;

    public static void main(String[] args)
    {
        new Client();
    }

    private Client()
    {
        try
        {
            link = new Socket("localhost", PORT);

            bufinput = new BufferedReader(new InputStreamReader(
                    link.getInputStream()));
            output = new PrintWriter(link.getOutputStream(), true);

            Scanner scInput = new Scanner(System.in);

            String message = null, response = null;
            System.out.println("Please enter your username & press enter: ");

            do
            {
                // To server
                message = scInput.nextLine();
                output.println(message);

                if (!message.equalsIgnoreCase("exit"))
                {
                    response = bufinput.readLine();

                    while (bufinput.ready())
                    {
                        response += "\n" + bufinput.readLine();
                    }

                    // From server
                    System.out.println(response);
                }
            } while (!message.equalsIgnoreCase("exit"));

            System.out.println("You requested session to end.");
        } catch (IOException e)
        {
            System.out.println("Connection to server lost.");
        } finally
        {
            closeConnection();
        }

        System.out.println("Ending chat.");
    } // End of my Run method

    private void closeConnection()
    {
        System.out.println("Closing connection.");

        try
        {
            if (bufinput != null)
                bufinput.close();

            if (output != null)
                output.close();

            if (link != null)
                link.close();

            System.out.println("Closed...");
        } catch (IOException e)
        {
            System.out.println("Unable to disconnect..");
            System.exit(1);
        }
    } // End of close connection method...
} // End of class.....
