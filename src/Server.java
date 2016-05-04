import java.net.*;
import java.io.*;
import java.util.Random;

/**
 * Created by Bryan on 5/1/16.
 */
public class Server {
    static ServerSocket serverSocket;
    protected final static int port = 13370;
    static Socket connection1;
    static Socket connection2;

    public static void main(String[] args) {
        boolean p1KeepPlaying, p2KeepPlaying;
        boolean playingGame = true;
        p1KeepPlaying = true;
        p2KeepPlaying = true;
        Random rand = new Random();
        int p1Total = 0;
        int p2Total = 0;


        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started.  Finding Clients...");

            while(playingGame) {
                connection1 = serverSocket.accept();
                DataInputStream inputFromClient1 = new DataInputStream(connection1.getInputStream());
                DataOutputStream replyToClient1 = new DataOutputStream(connection1.getOutputStream());
                System.out.println("Found Client 1.");

                connection2 = serverSocket.accept();
                DataInputStream inputFromClient2 = new DataInputStream(connection2.getInputStream());
                DataOutputStream replyToClient2 = new DataOutputStream(connection2.getOutputStream());
                System.out.println("Found Client 2.");

                System.out.println("Initiating Game...");

                while(p1KeepPlaying || p2KeepPlaying) {
                    if(p1KeepPlaying) {
                        // Ask Client 1 for move
                            replyToClient1.writeUTF("Your Move: It's your turn.  Enter 'H' for a Hit or 'S' to Stand.");
                            replyToClient1.writeUTF("Score:" + p1Total);
                            char p1Reply = inputFromClient1.readChar();
                        System.out.println("Player 1's Turn.");
                        System.out.println("Got " + p1Reply + " from Player 1.");


                        if(p1Reply == 'h' || p1Reply == 'H') {   //Randomly chooses a card face value
                            int hitNumber = rand.nextInt(13) + 1;
                            replyToClient1.writeUTF("Drew a " + hitNumber);
                            replyToClient1.writeUTF("Waiting for Player 2 now");
                            p1Total = p1Total + hitNumber;
                            System.out.println("Player 1's Score:" + p1Total);
                        } else if (p1Reply == 's' || p1Reply == 'S') {    //Player chooses to stay
                            System.out.println("Stand");
                            System.out.println("Player 1's current Total:" + p1Total);
                            p1KeepPlaying = false;
                        } else {
                            System.out.println("Please enter correct value");
                        }
                        replyToClient1.flush();

                        if(p1Total > 21) {
                            System.out.println("TOO HIGH!");
                            p1KeepPlaying = false;
                        }
                    }

                    if(p2KeepPlaying) {
                        // Ask Client 2 for move
                        replyToClient2.writeUTF("Your Move: It's your turn.  Enter 'H' for a Hit or 'S' to Stand.");
                        replyToClient2.writeUTF("Score:" + p2Total);
                        char p2Reply = inputFromClient2.readChar();
                        System.out.println("Player 2's Turn.");
                        System.out.println("Got " + p2Reply + " from Player 2.");

                        if(p2Reply == 'h' || p2Reply == 'H') {    //Randomly chooses a card face value
                            int hitNumber = rand.nextInt(13) + 1;
                            replyToClient2.writeUTF("Drew a " + hitNumber);
                            replyToClient2.writeUTF("Waiting for Player 1 now");
                            p2Total = p2Total + hitNumber;
                            System.out.println("Player 2's Score:" + p2Total);
                        } else if (p2Reply == 's' || p2Reply == 'S') {    //Player chooses to stay
                            System.out.println("Stand");
                            System.out.println("Player 2's current Total:" + p2Total);
                            p2KeepPlaying = false;
                        } else {
                            System.out.println("Please enter correct value");
                        }
                        replyToClient2.flush();

                        if(p2Total > 21) {
                            System.out.println("TOO HIGH!");
                            p2KeepPlaying = false;
                        }
                    }
                }
                System.out.println("Game Over.");

                //Sends Score and Winner to Players
                if((p1Total > p2Total && p1Total <= 21) || p2Total > 21 && p1Total <= 21) {
                    playingGame = false;
                    System.out.println("PLAYER 2 WINS!!!");
                    replyToClient1.writeUTF("Player 1's Score: " + p1Total);
                    replyToClient1.writeUTF("Player 2's Score: " + p2Total);
                    replyToClient1.writeUTF("Player 1 Wins!");

                    replyToClient2.writeUTF("Player 1's Score: " + p1Total);
                    replyToClient2.writeUTF("Player 2's Score: " + p2Total);
                    replyToClient2.writeUTF("Player 1 Wins!");
                } else if((p2Total > p1Total && p2Total <= 21) || p1Total > 21 && p2Total <= 21) {
                    playingGame = false;
                    System.out.println("PLAYER 2 WINS!!!");

                    replyToClient1.writeUTF("Player 1's Score: " + p1Total);
                    replyToClient1.writeUTF("Player 2's Score: " + p2Total);
                    replyToClient1.writeUTF("Player 2 Wins!");

                    replyToClient2.writeUTF("Player 1's Score: " + p1Total);
                    replyToClient2.writeUTF("Player 2's Score: " + p2Total);
                    replyToClient2.writeUTF("Player 2 Wins!");
                } else {
                    replyToClient1.writeUTF("You both tied.");
                    replyToClient2.writeUTF("You both tied.");
                    System.out.println("You both tied.");
                }
            }
        } catch(IOException ex) { }

        try {
            connection1.close();
            connection2.close();
        } catch(IOException ex) { }

    }
}