import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by Bryan on 5/1/16.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 13370;

        char selection;
        boolean win = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Client Initialized");

        try {
            System.out.println("Fetching Server Connection...");
            InetAddress address = InetAddress.getByName(host);
            Socket connection1 = new Socket(address, port);
            System.out.println("Connection Established.");

            OutputStream sendToServer1 = connection1.getOutputStream();
            DataOutputStream send1 = new DataOutputStream(sendToServer1);

            InputStream inFromServer1 = connection1.getInputStream();
            DataInputStream serverReply = new DataInputStream(inFromServer1);

            System.out.println("Game Started waiting for further instruction");

            while(win == false) {
                // got message from server
                System.out.println(serverReply.readUTF()); // just print the move instruction
                // send a move back
                System.out.println(serverReply.readUTF());
                selection = input.nextLine().charAt(0);
                send1.writeChar(selection);

                System.out.println(serverReply.readUTF());
                System.out.println(serverReply.readUTF());
                send1.flush();

            }
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }

    }


}