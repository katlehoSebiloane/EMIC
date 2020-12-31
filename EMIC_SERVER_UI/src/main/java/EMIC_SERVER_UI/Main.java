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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Rectangle;
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
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private XYChart.Series<String, Number> series = new XYChart.Series<>();
    final int WINDOW_SIZE = 1000;
    private StringBuffer Readout;
    private String[] Readings;
    private String FileName;
    private  PrintWriter printWriter = null;
    private int interation=0;
    private  Socket socket = null;
    private  hl7_Interface hl7 = null;
    private ArrayList<String> mess = new ArrayList<>();
    private Timer timer = new Timer();
    private Integer readingvalue = 0;
    private TimerTask sim = new TimerTask() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    System.out.println("this is running");
                    if(interation<Readings.length){
                        Platform.runLater(()->{
                            hl7.console.appendText(Readings[interation]);
                            Scanner sc = new Scanner(Readings[interation]);
                            while(sc.hasNextLine()){
                                String line = sc.nextLine();
                                sb.append(line+"\n");
                                String[] segment = line.split("[|]");
                                System.out.println(line);
                                if(segment[0].contains("OBX")&&(line.contains("MDC_PULS_OXIM_PULS_RATE")||line.contains("MDC_PULS_OXIM_SAT_O2")||line.contains("MDC_TTHOR_RESP_RATE")||line.contains("MDC_ECG_HEART_RATE"))){
                                    hl7.fp.getChildren().add(new EMIC_VITAL("Reading",segment[5],"Unit of measurement"));
                                    //series.getData().add(new XYChart.Data<>(segment[14], Integer.parseInt(segment[5])));
                                }if(segment[0].contains("OBX")&&(line.contains("MDC_ECG_ELEC_POTL_II"))){
                                    String[] values = segment[5].split("['^']");
                                    //series.getData().clear();

                                    for (String s:values
                                    ) {
                                        Integer puff = Integer.parseInt(s);
                                        readingvalue++;
                                        String test = readingvalue.toString();
                                        XYChart.Data data = new XYChart.Data(test,puff);
                                        Rectangle rect = new Rectangle(0, 0);
                                        rect.setVisible(false);
                                        data.setNode(rect);
                                        series.getData().add(data);
                                        if (series.getData().size() > WINDOW_SIZE)
                                            series.getData().remove(0);
                                    }
                                }
                            }
                            printWriter.println("sim");
                            Scanner sc1 = new Scanner(sb.toString());
                            while (sc1.hasNextLine()){
                                printWriter.println(sc1.nextLine());
                            }
                            printWriter.flush();
                            printWriter.close();
                            try {
                                socket = new Socket(InetAddress.getLocalHost(),15000);
                                printWriter = new PrintWriter(socket.getOutputStream(),true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            interation++;

                        });
                    }else{
                        timer.cancel();
                        System.out.println("timer cancelled simulation complete!");
                        Platform.runLater(()->{
                            hl7.console.appendText("Simulation Complete!!");

                        });

                    }
                    return 0;
                }
            };
            Thread t = new Thread(task);
            t.start();
        }
    };

    private Integer Read() throws IOException {
        printWriter.println("SHOW");
        System.out.println("Getting ready to read\n\n");
        StringBuffer line = new StringBuffer();
        Scanner sc = new Scanner(bufferedReader);
        while(sc.hasNextLine()){
            String lineRead = sc.nextLine();
            line.append(lineRead+"\n");
            mess.add(lineRead);
        }
        Readout = line;
        Readings = Readout.toString().split("\u001C");
        for (String s:mess
        ) {
            String[] segment = s.split("[|]");
            if(segment[0].contains("OBX")&&(s.contains("MDC_PULS_OXIM_PULS_RATE")||s.contains("MDC_PULS_OXIM_SAT_O2")||s.contains("MDC_TTHOR_RESP_RATE")||s.contains("MDC_ECG_HEART_RATE"))){
                ArrayList<String> vitals = this.Map(s);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        //Update UI here
                        hl7.fp.getChildren().add(new EMIC_VITAL(vitals.get(0),segment[5],vitals.get(1)));
                    }
                });
            }else{
                continue;
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
        printWriter.println("CONNECT:"+FileName+"\n\n");
        hl7.console.appendText(bufferedReader.readLine()+"\n\n");
        mess.clear();
        return 0;
    }

    private ArrayList<String> Map(String s){
        ArrayList<String> t = new ArrayList<>();
        if(s.contains("MDC_ECG_HEART_RATE")){
            t.add("Heart Rate");
            t.add("bpm");
        }
        if(s.contains("MDC_TTHOR_RESP_RATE")){
            t.add("Respiration Rate");
            t.add("rpm");
        }
        if(s.contains("MDC_PULS_OXIM_SAT_O2")){
            t.add("Oxygen Saturation");
            t.add("%");
        }
        if(s.contains("MDC_PULS_OXIM_PULS_RATE")){
            t.add("Pulse Rate");
            t.add("bpm");
        }

        return t;
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        hl7 = new hl7_Interface();
        lineChart.getData().add(series);
        lineChart.setAnimated(false);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
       // lineChart.setMinWidth(1000);
        lineChart.setMinHeight(400);
        lineChart.setTitle("Vital Sign");

        hl7.fp.getChildren().add(lineChart);
        hl7.navigation.Connect.setOnAction(e->{
            try {
                socket = new Socket(InetAddress.getLocalHost(),15000);
                printWriter = new PrintWriter(socket.getOutputStream(),true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Task<Integer> task = new Task<Integer>(){
                    @Override
                    protected Integer call() throws IOException {
                        System.out.println("Done Client Side Setup");
                        FileName = hl7.navigation.Destination.getText();
                        printWriter.println("CONNECT:"+FileName+"\n\n");
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
                printWriter.println("CONNECT:"+FileName+"\n\n");
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
                hl7.fp.getChildren().clear();
                hl7.fp.getChildren().add(lineChart);
                hl7.console.clear();
                timer.scheduleAtFixedRate(sim,100,1000);
                hl7.console.appendText("\n Starting Simulation \n");
        });


        Scene s = new Scene( hl7,1920,1020);
        s.getStylesheets().add("file:///home/katleho/Documents/emic/EMIC_SERVER_UI/src/main/java/EMIC_SERVER_UI/styles.css");

        primaryStage.setScene(s);
        primaryStage.setTitle("HL7 TEST SERVER");
        primaryStage.show();
    }


}
