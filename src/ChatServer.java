import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket serverSocket;
    private ArrayList<PrintWriter> sendList;

    public ChatServer() {
        // Create a new Server Socket on port 8080 to listen for clients
        try {
            this.serverSocket = new ServerSocket(8080);
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}
