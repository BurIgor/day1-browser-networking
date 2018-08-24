package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    static final int PORT_NUMBER = 1234;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.println("Start working...");
        Socket socketClient = serverSocket.accept();
        serverSocket.close();

        while (socketClient.isConnected()){

            BufferedReader clientThread = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            PrintWriter streamToClient = new PrintWriter(socketClient.getOutputStream());

            if (clientThread.ready()) {
                String clientMessage = clientThread.readLine();
                System.out.println("Received message: " + clientMessage);
                streamToClient.println(clientMessage);
                streamToClient.flush();
            }
        }
        System.out.println("Finish working...");
    }
}
