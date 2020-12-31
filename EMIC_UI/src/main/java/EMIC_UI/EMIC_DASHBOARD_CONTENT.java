package EMIC_UI;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.layout.*;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class EMIC_DASHBOARD_CONTENT extends BorderPane {

    // Top Section Header with the emergency status and eta
    private final String Scenename = "Activity";
    // the gridpane is use the hold the vital sign cards
    private FlowPane fp = new FlowPane();

    public final CategoryAxis xAxis = new CategoryAxis();
    public final NumberAxis yAxis = new NumberAxis();
    public final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
    public XYChart.Series series = new XYChart.Series();


    public final CategoryAxis xAxis1 = new CategoryAxis();
    public final NumberAxis yAxis2 = new NumberAxis();
    public final LineChart<String,Number> lineChart1 = new LineChart<String,Number>(xAxis1,yAxis2);
    public XYChart.Series series1 = new XYChart.Series();

    public final CategoryAxis xAxis3 = new CategoryAxis();
    public final NumberAxis yAxis4 = new NumberAxis();
    public LineChart<String,Number> lineChart3 = new LineChart<String,Number>(xAxis3,yAxis4);
    public XYChart.Series series2 = new XYChart.Series();

    public final CategoryAxis xAxis5 = new CategoryAxis();
    public final NumberAxis yAxis6 = new NumberAxis();
    public final LineChart<String,Number> lineChart4 = new LineChart<String,Number>(xAxis5,yAxis6);
    public XYChart.Series series3 = new XYChart.Series();
    private String[] Readings;
    private  PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    private StringBuffer Readout;
    private int interation=0;
    private Integer readingvalue = 0;
    final int WINDOW_SIZE = 1000;
    private  Socket socket = null;

    private void Read() throws IOException {
        try {
            socket = new Socket(InetAddress.getLocalHost(),15000);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Task<Integer> task = new Task<Integer>(){
                @Override
                protected Integer call() throws IOException {
                    System.out.println("Done Client Side Setup");
                    String FileName = "Sim";
                    printWriter.println("CONNECT:"+FileName+"\n\n");
                    String line1 = bufferedReader.readLine();
                    System.out.println(line1+"\n\n");
                    printWriter.println("SHOW");
                    System.out.println("Getting ready to read\n\n");
                    StringBuffer line = new StringBuffer();
                    Scanner sc = new Scanner(bufferedReader);
                    while(sc.hasNextLine()){
                        String lineRead = sc.nextLine();
                        line.append(lineRead+"\n");
                    }
                    Readout = line;
                    Readings = Readout.toString().split("\u001C");

                    System.out.println("Done reading");

                    socket.close();
                    socket = new Socket(InetAddress.getLocalHost(),15000);
                    printWriter = new PrintWriter(socket.getOutputStream(),true);
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    return 0;
                }
            };
            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    };

    private Timer timer = new Timer();
    private Timer reader = new Timer();
    private TimerTask redded = new TimerTask() {
        @Override
        public void run() {
            try {
                Read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private TimerTask sim = new TimerTask() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();

            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    //System.out.println("this is running");
                    if(interation<Readings.length){
                        Platform.runLater(()->{
                            Scanner sc;
                            sc = new Scanner(Readings[interation]);

                            while(sc.hasNextLine()){
                                String line = sc.nextLine();
                                sb.append(line+"\n");
                                String[] segment = line.split("[|]");
                                System.out.println("Getting ready to plot data hawu: "+line);
                                if(segment[0].contains("OBX")&&(line.contains("MDC_PULS_OXIM_PULS_RATE")||line.contains("MDC_PULS_OXIM_SAT_O2")||line.contains("MDC_TTHOR_RESP_RATE")||line.contains("MDC_ECG_HEART_RATE"))){
                                    //series.getData().add(new XYChart.Data<>(segment[14], Integer.parseInt(segment[5])));
                                }if(segment[0].contains("OBX")&&(line.contains("MDC_ECG_ELEC_POTL_II"))){
                                    String[] values = segment[5].split("['^']");
                                    //series.getData().clear();

                                    for (String s:values
                                    ) {
                                        Integer puff = Integer.parseInt(s);
                                        //System.out.println(s);
                                        readingvalue++;
                                        String test = readingvalue.toString();
                                        //System.out.println("UPDATING CHART....");
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

                            interation++;
                        });
                    }else{
                        //timer.cancel();
                        //Read();
                        interation=0;
                        System.out.println("timer cancelled simulation complete!");
                        Platform.runLater(()->{
                            System.out.println("Simulation Completed!!!!!");
                        });

                    }
                    return 0;
                }
            };
            Thread t = new Thread(task);
            t.start();
        }
    };

    EMIC_DASHBOARD_CONTENT(Stage PrimaryStage){
        try {
            socket = new Socket(InetAddress.getLocalHost(),15000);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Task<Integer> task = new Task<Integer>(){
                @Override
                protected Integer call() throws IOException {
                    System.out.println("Done Client Side Setup");
                    String FileName = "Sim";
                    printWriter.println("CONNECT:"+FileName+"\n\n");
                    String line = bufferedReader.readLine();
                    System.out.println(line+"\n\n");
                    return 0;
                }
            };
            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart.setTitle("Heart Rate");
        lineChart.setMinWidth(700);
        lineChart.setMaxHeight(500);
        lineChart.setAnimated(false);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        lineChart.getData().add(series);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart1.setTitle("Respiratory Rate");
        lineChart1.setMinWidth(700);
        lineChart1.setMaxHeight(500);
        lineChart.getData().add(series1);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart3.setTitle("Blood Pressure");
        lineChart3.setMinWidth(700);
        lineChart3.setMaxHeight(500);
        lineChart.getData().add(series2);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart4.setTitle("Chart");
        lineChart4.setMinWidth(700);
        lineChart4.setMaxHeight(500);
        lineChart.getData().add(series3);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        fp.getChildren().addAll(lineChart,lineChart1,lineChart3,lineChart4);
        fp.setHgap(10);
        fp.setVgap(10);
        fp.setPadding(new Insets(50,0,0,0));

        //THIS IS WHERE WE ADD ALL OF THE ELEMENTS TO THE SCREEN
        Header HeaderTop = new Header(Scenename);

        setTop(HeaderTop);
        setCenter(fp);
        setMinWidth(1131);
        prefHeightProperty().bind(PrimaryStage.heightProperty());
        setStyle("-fx-background-color:#ffffff");
        timer.scheduleAtFixedRate(sim,2000,1500);
        reader.scheduleAtFixedRate(redded,100,500);


    }

}
