package udp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HTTPServer {

    static final int PORT_NUMBER = 1234;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.println("Start working...");
        Socket socketClient = serverSocket.accept();
        serverSocket.close();

        while (socketClient.isConnected()){

            BufferedReader clientThread = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            PrintWriter streamToClient = new PrintWriter(socketClient.getOutputStream());

            Date date = new Date();

            if (clientThread.ready()) {
                String clientMessage = clientThread.readLine();
                System.out.println("Received message: " + clientMessage);
                String serverReply = "HTTP/1.1 200 OK\r\nServer: Apache\r\nDate: " + date.toString() + "\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" + " \r\n" + clientMessage + "\r\n";
                streamToClient.println(serverReply);
                streamToClient.flush();
            }
        }
        System.out.println("Finish working...");
    }
}
