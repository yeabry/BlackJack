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
            Socket connection1 = new Socket(address, port);
            Socket connection2 = new Socket(address, port);


            OutputStream sendToServer1 = connection1.getOutputStream();
            DataOutputStream send1 = new DataOutputStream(sendToServer1);

            OutputStream sendToServer2 = connection2.getOutputStream();
            DataOutputStream send2 = new DataOutputStream(sendToServer2);

            InputStream inFromServer1 = connection1.getInputStream();
            DataInputStream serverReply = new DataInputStream(inFromServer1);

            InputStream inFromServer2 = connection2.getInputStream();
            DataInputStream serverReply2 = new DataInputStream(inFromServer2);

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
