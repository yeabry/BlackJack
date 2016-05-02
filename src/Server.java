import java.net.*;
import java.io.*;


/**
 * Created by Bryan on 5/2/16.
 */
public class Server {
    static ServerSocket serverSocket;
    protected final static int port = 13370;
    static Socket connection;

    public static void main(String[] args) {
        //TODO: Create Variables
        boolean win = false;




        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Initialized");

            while(true) {
                connection = serverSocket.accept();
                DataInputStream inputFromClient = new DataInputStream(connection.getInputStream());
                DataOutputStream replyToClient = new DataOutputStream(connection.getOutputStream());

                while(win == false) {
                    // TODO: Put game code here





                }
            }
        } catch(IOException ex) { }

        try {
            connection.close();
        } catch(IOException ex) { }
    }
}
