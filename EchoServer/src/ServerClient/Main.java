package ServerClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true){

                //--New Thread for every connection--//
                new Echoer(serverSocket.accept()).start();

                //Same as :
/*              Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();*/

            }

        } catch (IOException e) {
            System.out.println("Server exceptions" + e.getMessage());
        }
    }

}

