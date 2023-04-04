import java.io.BufferedReader;
import java.io.IOException;

public class ClientHandler implements Runnable {
    private ChatServer chatServer;
    private BufferedReader clientReader;

    public ClientHandler(ChatServer chatServer, BufferedReader clientReader) {
        this.chatServer = chatServer;
        this.clientReader = clientReader;
    }

    @Override
    public void run() {
        watchClientInput();
    }

    private void watchClientInput() {
        String message;

        try {
            while ((message = this.clientReader.readLine()) != null) {
                System.out.println("Received: " + message + "\n... Sending ..." );
                this.chatServer.sendToEveryone(message);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }

    }
}
