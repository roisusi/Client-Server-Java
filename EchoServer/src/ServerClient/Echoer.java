package ServerClient;

import javax.swing.plaf.TableHeaderUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.util.function.DoubleUnaryOperator;

public class Echoer extends Thread{
    private Socket socket;
    private DataBase db;

    public Echoer(Socket socket) {
        //Communicate with the Client
        this.socket = socket;
        db = new DataBase();
    }

    @Override
    public void run(){
        try {
            System.out.println("Client with port: " + socket.getPort() + " has Entered");
            //Read from the Server Socket
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                String echoString = input.readLine();

                //Connection to DataBase
                if (echoString.equals("connect")){
                    try {
                        db.connect();
                        output.println("Connection Establish Welcome to DataBase Test - " + socket.getPort() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (echoString.equals("exit")) {
                    System.out.println("Client with port: " + socket.getPort() + " has exit");
                    break;
                }
                else {
                    System.out.println("Message has been received : " + echoString);
                }

//                try {
//                    //Delay for multi conections
//                    Thread.sleep(15000);
//                } catch (InterruptedException e){
//                System.out.println("Thread Interrupted");
//                }

                output.println("Echo from server: " + echoString);
            }
        } catch (IOException e){
            System.out.println("Oops: " + e.getMessage());

        }finally {
            //Close Manually the Socket
            try {
                socket.close();
            } catch (IOException e) {
                //
            }
        }

    }
}
