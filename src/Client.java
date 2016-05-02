import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by Bryan on 5/2/16.
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
            InetAddress address = InetAddress.getByName(host);
            Socket connection = new Socket(address, port);

            OutputStream sendToServer = connection.getOutputStream();
            DataOutputStream send = new DataOutputStream(sendToServer);

            InputStream inFromServer = connection.getInputStream();
            DataInputStream serverReply = new DataInputStream(inFromServer);

            while(win == false) {
                System.out.println("It's your turn.  Enter 'H' for a Hit or 'S' to Stand.\n");
                selection = input.nextLine().charAt(0);
                //System.out.println(selection);            Test
            }




        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }

    }


}
