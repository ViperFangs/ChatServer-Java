import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket serverSocket;
    private ArrayList<PrintWriter> sendList;
    private boolean serverRunning;

    public ChatServer() {
        // Create a new Server Socket on port 8080 to listen for clients
        try {
            this.serverSocket = new ServerSocket(8080);
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Method responsible to start the server
     */
    public void start() {
        this.serverRunning = true;
        System.out.println("Starting the Chat Server...");
        while(serverRunning) {
            Socket clientConnection = null;

            System.out.println("Waiting for a Client Connection ...");

            try {
                clientConnection = serverSocket.accept();
            } catch (IOException error) {
                error.printStackTrace();
            }

            BufferedReader clientReader = null;

            try {
                clientReader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            } catch (IOException error) {
                error.printStackTrace();
            }

            System.out.println("Building Client Thread.");
            ClientHandler handler = new ClientHandler(this, clientReader);
            Thread newClient = new Thread(handler);
            newClient.start();

            System.out.println("Thread started ... Looking for new clients.");
        }
    }

    /**
     * Method responsible to broadcast message to every connected client
     * @param message is the string that will be sent to all the clients
     */
    public void sendToEveryone(String message) {
        for (PrintWriter client : this.sendList) {
            client.print(message);
            client.flush();
        }
    }
}
