package ServerClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            //Communicate with the Client
            Socket socket = serverSocket.accept();
            System.out.println("Client Accepted");
            //Read from the Server Socket
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            while (true){
                String echoString = input.readLine();
                if(echoString.equals("exit")){
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
            
        } catch (IOException e) {
            System.out.println("Server exceptions" + e.getMessage());
            e.printStackTrace();
        }
    }

}
