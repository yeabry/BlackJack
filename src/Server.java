import java.net.*;
import java.io.*;


/**
 * Created by Bryan on 5/2/16.
 */
public class Server {
    static ServerSocket serverSocket;
    protected final static int port = 13370;
    static Socket connection1;
    static Socket connection2;

    public static void main(String[] args) {
        //TODO: Create Variables
        int[] cardDeck = {2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,11};
        boolean win = false;




        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started.  Finding Clients...");

            while(true) {
                connection1 = serverSocket.accept();
                DataInputStream inputFromClient1 = new DataInputStream(connection1.getInputStream());
                DataOutputStream replyToClient1 = new DataOutputStream(connection1.getOutputStream());
                System.out.println("Found Client 1.");

                connection2 = serverSocket.accept();
                DataInputStream inputFromClient2 = new DataInputStream(connection2.getInputStream());
                DataOutputStream replyToClient2 = new DataOutputStream(connection2.getOutputStream());
                System.out.println("Found Client 2.");

                while(win == false) {
                    // TODO: Put game code here





                }
            }
        } catch(IOException ex) { }

        try {
            connection1.close();
            connection2.close();
        } catch(IOException ex) { }
    }
}
