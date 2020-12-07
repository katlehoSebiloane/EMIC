/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMIC_SERVER_UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author 27828
 */
public class Main extends Application {

    private  BufferedReader bufferedReader= null;
    private  PrintWriter printWriter = null;
    private  Socket socket = null;
    private  hl7_Interface hl7 = null;
    private ArrayList<String> mess = new ArrayList<>();
    private Timer timer = new Timer();
    private TimerTask sim = new TimerTask() {
        @Override
        public void run() {
            try {
                Read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private Integer Read() throws IOException {
        printWriter.println("SHOW");
        System.out.println("Getting ready to read\n\n");
        StringBuffer line = new StringBuffer();
        Scanner sc = new Scanner(bufferedReader);
        while(sc.hasNextLine()){
            String lineRead = sc.nextLine();
            line.append(lineRead);
            mess.add(lineRead);
        }
        for (String s:mess
        ) {
            String[] segment = s.split("[|]");
            if(!segment[0].equals("OBX")){
                continue;
            }else{
                String[] vitals = segment[3].split("\\^");


                System.out.println(vitals[1]);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        //Update UI here
                        hl7.fp.getChildren().add(new EMIC_VITAL(vitals[1],segment[5],"%"));

                    }
                });
            }
        }

        System.out.println("Done reading");
        hl7.console.appendText(line+"\n\n\n");
        hl7.console.appendText("Restarting Server.......\n\n");
        socket.close();
        socket = new Socket(InetAddress.getLocalHost(),15000);
        printWriter = new PrintWriter(socket.getOutputStream(),true);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        hl7.console.appendText("Client Side Setup Completed..........\n\n");
        printWriter.println("CONNECT\n\n");
        hl7.console.appendText(bufferedReader.readLine()+"\n\n");
        mess.clear();
        return 0;
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        hl7 = new hl7_Interface();

        hl7.navigation.Connect.setOnAction(e->{
            try {
                socket = new Socket(InetAddress.getLocalHost(),15000);
                printWriter = new PrintWriter(socket.getOutputStream(),true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Task<Integer> task = new Task<Integer>(){
                    @Override
                    protected Integer call() throws IOException {
                        System.out.println("Done Client Side Setup");
                        printWriter.println("CONNECT\n\n");
                        String line = bufferedReader.readLine();
                        hl7.console.appendText(line+"\n\n");
                        return 0;
                    }
                };
                Thread t = new Thread(task);
                t.setDaemon(true);
                t.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        hl7.navigation.Read.setOnAction(e->{
            printWriter.println("SHOW");
            Task<Integer> task = new Task<Integer>(){
                @Override
                protected Integer call() throws IOException {
                    Read();
                    return 0;
                }
            };
                Thread t = new Thread(task);
                t.setDaemon(true);
                t.start();


        });

        hl7.navigation.Test.setOnAction(e->{
            String test_message = "MSH|^~`&|ECG REPORTING|ROCHESTER|ERIS|ROCHESTER|20110621050440||ORU^R01|20110621050440|P|2.1\n" +
                    "PID|||0010135050085||KATLEHO^SEBILOANE|||F\n" +
                    "OBR|||211088491|0^ADULT^ROCHECG|||20110620170631|||||||||M999999^^^^^^^RACFID||||||20110621060232||EC|F|||||||M999999^LASTNAME MD^FIRSTNAME^^^^^RACFID\n" +
                    "OBX||ST|67^HEART RATE^BPM|||\n"+
                    "OBX||ST|5^ETCO2^%|||\n"+
                    "OBX||ST|120:80^BLOOD PRESSURE^MMHG|||\n"+
                    "OBX||ST|16^RATE OF RESPIRATION^BPM|||\n"+
                    "OBX||ST|37^TEMPERATURE^DEGREES|||\n"+
                    "OBX||ST|98^SO2^%|||\n";

            Task<Integer> task = new Task<Integer>(){
                @Override
                protected Integer call() throws IOException {
                    printWriter.println(test_message);
                    hl7.console.appendText("Sending Test Message.........."+"\n\n");
                    String line = bufferedReader.readLine();
                    if(line.equals(null)){
                        hl7.console.appendText("nothing read attempting to read again!!");
                        line = bufferedReader.readLine();
                    }

                    if(line.equals("The Test Message Has Been Received")){
                        Scanner sc = new Scanner(test_message);
                        while(sc.hasNextLine()){
                            printWriter.println(sc.nextLine());
                        }
                        socket.shutdownOutput();
                        socket.shutdownInput();
                        sc.close();
                        printWriter.close();
                    }else{
                        hl7.console.appendText("Restart and Read the file!!!!");
                    }


                    hl7.console.appendText(line+"\n\n");
                    return 0;
                }
            };
            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();


        });



        hl7.navigation.Close.setOnAction(e->{
            printWriter.close();
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            hl7.console.appendText("Closing socket safely........\n\n\n");

        });

        hl7.navigation.Restart.setOnAction(e->{
            try{
                socket.shutdownInput();
                socket.shutdownOutput();
                hl7.console.setText("");
                hl7.console.appendText("Closing all sockets and streams.......\n\n");
                printWriter.close();
                bufferedReader.close();
                socket.close();
                hl7.console.appendText("Restarting Server.......\n\n");
                socket = new Socket(InetAddress.getLocalHost(),15000);
                printWriter = new PrintWriter(socket.getOutputStream(),true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                hl7.console.appendText("Client Side Setup Completed..........\n\n");
                printWriter.println("CONNECT\n\n");
                hl7.console.appendText(bufferedReader.readLine()+"\n\n");
                hl7.console.setText("Connected\n\n");
                hl7.fp.getChildren().clear();
                mess.clear();
                timer.cancel();

            }catch (Exception e1){
                e1.printStackTrace();
            }


        });

        hl7.navigation.Simulate.setOnAction(e->{
                timer.scheduleAtFixedRate(sim,100,10000);
                hl7.console.appendText("\n Starting Simulation \n");
        });


        Scene s = new Scene( hl7,1920,1020);
        s.getStylesheets().add("file:///home/katleho/Documents/emic/EMIC_SERVER_UI/src/main/java/EMIC_SERVER_UI/styles.css");

        primaryStage.setScene(s);
        primaryStage.setTitle("HL7 TEST SERVER");
        primaryStage.show();
    }


}