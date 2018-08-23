package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDPClient {

    static final int PORT_NUMBER = 1234;
    public static void main(String[] args) throws Exception{

        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(3000);
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("\nMessage (quit = stop): ");
            String message = scanner.nextLine();
            if (message.toLowerCase().equals("quit")) break;

            DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), inetAddress, PORT_NUMBER);
            datagramSocket.send(sendPacket);
            byte[] receive = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);

            try{
                datagramSocket.receive(receivePacket);
            } catch (SocketTimeoutException t) {
                System.out.println("\nNo server answer.\nTry again.");
                continue;
            }

            String answer = new String(receivePacket.getData());
            System.out.println("Server answer: " + answer);
        }
    }
}
