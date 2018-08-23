package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    static final int PORT_NUMBER = 1234;
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket(PORT_NUMBER);

        while (true){
            byte[] receive = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
            datagramSocket.receive(receivePacket);
            InetAddress ip = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String message = new String(receivePacket.getData());
            System.out.println("Received message: " + message);
            String answer = "Server received message: " + message.trim();
            DatagramPacket sendPacket = new DatagramPacket(answer.getBytes(), answer.length(), ip, port);
            datagramSocket.send(sendPacket);
        }
    }
}
