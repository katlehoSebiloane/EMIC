package EMIC_SERVER;

import java.io.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Handler implements Runnable{
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private File logs = null;
    private FileInputStream fis = null;
    private Scanner sc = null;
    private FileWriter logWriter = null;
    private Socket s;
    private String ID;

    public Handler(Socket socket) throws IOException {
        this.s = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        printWriter = new PrintWriter(s.getOutputStream(),true);
    }

    @Override
    public void run() {

        while(true){
            try {
                String Command = bufferedReader.readLine();
                //System.out.println(Command);
                String Type = Command.split("[|]")[0];

                if(Command.equals("SHOW")){

                    String Message;
                    File logs = new File("./"+ID);
                    fis = new FileInputStream(logs);
                    sc = new Scanner(fis);

                        while(sc.hasNextLine()){
                            Message = sc.nextLine();
                            printWriter.println(Message);
                        }
                        s.shutdownInput();
                        s.shutdownOutput();
                        s.close();
                        System.out.println("\nDone Reading ..........|||||||||||||||||||||||\n");

                }

                if(Command.contains("CONNECT")){
                    System.out.println("NEW CONNECTION DETECTED");
                    printWriter.println("Connected.......\n");
                    printWriter.flush();
                    ID = Command.split("[:]")[1]+".txt";
                    System.out.println(ID);
                }

                if(Type.contains("MSH")){
                    if(s.getRemoteSocketAddress().toString().split("[.]")[3].contains("119")){
                        ID = "D3.txt";
                    }
                    if(s.getRemoteSocketAddress().toString().split("[.]")[3].contains("115")){
                        ID = "D6.txt";
                    }
                    System.out.println("MESSAGE EVENT DETECTED!");
                    printWriter.println("The Test Message Has Been Received");
                    Scanner sc = new Scanner(bufferedReader);
                    while(sc.hasNextLine()){
                        String mess = sc.nextLine()+"\n";
                        logWriter = new FileWriter(ID,true);
                        logWriter.write(mess,0,mess.length());
                        logWriter.close();
                        System.out.println("read a line....");
                    }

                    System.out.println("wrote to the file");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
