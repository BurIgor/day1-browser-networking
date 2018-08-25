package udp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HTTPClient {

    static final int PORT_NUMBER = 1234;

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", PORT_NUMBER);

        Scanner scanner = new Scanner(System.in);
        PrintWriter messageToServer = new PrintWriter(socket.getOutputStream());
        BufferedReader messageFromServer  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(" Start connect...");
        while (true){
            System.out.print("\nInput your message: ");
            String userMessage = scanner.nextLine();
            messageToServer.println(userMessage);
            messageToServer.flush();
            Thread.sleep(1000);
            if (messageFromServer.ready()){
                StringBuffer serverMessage = new StringBuffer();
                String str;
                while (!(str = messageFromServer.readLine()).isEmpty()) serverMessage.append(str + "\n");

                System.out.println("Server's received : " + serverMessage.toString());
                StringBuffer serverHTML = new StringBuffer();
            }
        }
    }
}
