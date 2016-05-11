package messagingAplicationPackage.Application;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import messagingAplicationPackage.service.Service;

public class Server {

    private ServerSocket server;
    private Socket connection;

    //set up and run the server
    public void startRunning() {
        try {
            server = new ServerSocket(9999, 50);
            while (true) {
                try {
                    
                    waitForConnection();
                    setupClientService();

                } catch (EOFException eofException) {
                    System.out.println("\n Server ended the connection! ");
                } finally {
                    closeConnections();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    //wait for connection, then display connection information
    private void waitForConnection() throws IOException {
        System.out.println(" Waiting for someone to connect... \n");
        connection = server.accept();
        System.out.println(" Now connected to " + connection.getInetAddress().getHostName());
    }

    // Setup Client Services
    public void setupClientService() {
        System.out.println(" You are now connected! \n");
        Service service = new Service(connection);
        Thread thread = new Thread(service);
        thread.start();
    }

    // close stream and sockets after you are done
    private void closeConnections() {
        System.out.println(" Closing connections... \n");
        try {
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.startRunning();
    }
}
