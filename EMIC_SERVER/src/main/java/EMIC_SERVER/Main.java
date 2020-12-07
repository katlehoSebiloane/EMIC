/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMIC_SERVER;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author KATLEHO SEBILOANE
 */
public class Main {
    private static Socket socket = null;
    private static ServerSocket server = null;

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(15000);
        System.out.println("The server is up and running....");
        System.out.println(InetAddress.getLocalHost());

        while(true){
            socket = server.accept();
            System.out.println("Connected!!");
            Handler hlr = new Handler(socket);
            Thread t = new Thread(hlr);
            t.start();
        }

    }

}
