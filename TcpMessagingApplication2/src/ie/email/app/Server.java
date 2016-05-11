package our.email.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Paul Murphy
 */

public class Server
{
    private static final int    PORT = 2012;
    private static ServerSocket listener;
    private static Socket       link = null;

    public static void main(String[] args)
    {
        new Server();
    }

    private Server()
    {
        try
        {
            listener = new ServerSocket(PORT);

            while (true)
            {
                link = listener.accept();

//                String senderIp0 = link.getLocalAddress().getHostName();
//                String senderIp1 = InetAddress.getLocalHost().getHostName();
//                String senderIp2 = InetAddress.getLocalHost()
//                        .getCanonicalHostName();
//                int senderPort0 = link.getLocalPort();
//                int senderPort1 = link.getPort();
//                int senderPort2 = listener.getLocalPort();
//                System.out.println("Client connected from IP Address1: "
//                        + senderIp0);
//                System.out.println("Client connected from Host Computer Name: "
//                        + senderIp1);
//                System.out.println("Client connected from Host Computer Name: "
//                        + senderIp2);
//                System.out.println("Client connected from my PORT: "
//                        + senderPort0);
//                System.out
//                        .println("Client connected from PORT: " + senderPort1);
//                System.out
//                        .println("(ServerSocket) Client connected from PORT: "
//                                + senderPort2);

                Provider connect = new Provider(link);

                Thread thread = new Thread(connect);
                thread.start();
            }
        } catch (IOException e)
        {
            System.out.println("Error: Won't close. " + e);
        } finally
        {
            closeConnection();
        }
    } // End of myRun method.

    private static void closeConnection()
    {
        try
        {
            System.out.println("Closing connection.");
            link.close();
        } catch (IOException e)
        {
            System.out.println("Unable to disconnect.");
            System.exit(1);
        }
    }
}
