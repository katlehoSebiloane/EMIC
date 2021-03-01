package EMIC_UI;

import com.sun.javafx.charts.Legend;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.*;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
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
    public FlowPane fp = new FlowPane();
    public String HeartRate;
    public String PulseRate;
    public String so2;
    public String resp;
    public final CategoryAxis xAxis = new CategoryAxis();
    public final NumberAxis yAxis = new NumberAxis();
    public final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
    public XYChart.Series series1 = new XYChart.Series();


    public final CategoryAxis xAxis1 = new CategoryAxis();
    public final NumberAxis yAxis2 = new NumberAxis();
    public final LineChart<String,Number> lineChart1 = new LineChart<String,Number>(xAxis1,yAxis2);
    public XYChart.Series series2 = new XYChart.Series();

    public final CategoryAxis xAxis3 = new CategoryAxis();
    public final NumberAxis yAxis4 = new NumberAxis();
    public LineChart<String,Number> lineChart3 = new LineChart<String,Number>(xAxis3,yAxis4);
    public XYChart.Series series3 = new XYChart.Series();

    Integer i1 = 0;
    Integer i2 = 0;
    Integer i3 = 0;
    Integer counter=0;
    private String[] Readings;
    private  PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    final int WINDOW_SIZE = 1000;
    private  Socket socket = null;
    private Timer requestTimer = new Timer();
    private String database;
    private TimerTask simulator = new TimerTask() {
        @Override
        public void run() {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                counter++;
                                printWriter = new PrintWriter(socket.getOutputStream(),true);
                                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                System.out.println("Done Client Side Setup");
                                //printWriter.println("CONNECT|"+"D6"+"\n\n");
                                printWriter.println("SIMULATOR|"+database+"\n\n");
                                Scanner scanner = new Scanner(bufferedReader);
                                while(scanner.hasNextLine()){
                                   // System.out.println("reading every message segment");
                                    String message_segment = scanner.nextLine();
                                    //System.out.println(message_segment);

                                    System.out.println(message_segment);

                                    if(message_segment.contains("MDC_ECG_HEART_RATE")){
                                        String mod1 = message_segment.replace("{{","");
                                        String mod2 = mod1.replace("}}","");
                                        String mod3 = mod2.replace("Document","");
                                        String[] message = mod3.split("[,]");
                                        String reading = message[5];
                                        String reading2 = message[6];
                                        String[] process = reading.split("[|]");
                                        String reader = process[6];
                                        String reader2 = reading2.split("[|]")[6];
                                        HeartRate = reader;
                                        PulseRate = reader2;
                                        System.out.println("REAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDING: "+HeartRate);

                                    }


                                    if(message_segment.contains("MDC_PULS_OXIM_SAT_O2")){
                                        String mod1 = message_segment.replace("{{","");
                                        String mod2 = mod1.replace("}}","");
                                        String mod3 = mod2.replace("Document","");
                                        String[] message = mod3.split("[,]");
                                        String reading = message[5];
                                        String reading2 = message[6];
                                        String[] process = reading.split("[|]");
                                        String reader = process[6];
                                        String reader2 = reading2.split("[|]")[6];
                                        so2 = reader;
                                        PulseRate = reader2;
                                        System.out.println("REAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDING: "+HeartRate);

                                    }



                                    if(message_segment.contains("MDC_IMPED_TTHOR")){
                                        String mod1 = message_segment.replace("{{","");
                                        String mod2 = mod1.replace("}}","");
                                        String mod3 = mod2.replace("Document","");
                                        String[] message = mod3.split("[,]");
                                        String reading = message[5];
                                        String reading2 = message[6];
                                        String[] process = reading.split("[|]");
                                        String reader = process[6];
                                        String[] values = reader.split("['^']");

                                        for (String r:values
                                        ) {
                                            //System.out.println(r);
                                            i2++;
                                            int value_real_value = Integer.parseInt(r);
                                            Integer finalI = i2;
                                            Platform.runLater(()->{
                                                if(series2.getData().size()>1100){
                                                    series2.getData().remove(0,100);
                                                }
                                                series2.getData().add(new XYChart.Data(finalI.toString(), value_real_value));
                                            });



                                        }
                                    }

                                    if(message_segment.contains("MDC_ECG_ELEC_POTL_II")){
                                        String mod1 = message_segment.replace("{{","");
                                        String mod2 = mod1.replace("}}","");
                                        String mod3 = mod2.replace("Document","");
                                        String[] message = mod3.split("[,]");
                                        String reading = message[5];
                                        String reading2 = message[6];
                                        String[] process = reading.split("[|]");
                                        String reader = process[6];
                                        String[] values = reader.split("['^']");
                                        for (String r:values
                                        ) {
                                           // System.out.println(r);
                                            i3++;
                                            int value_real_value = Integer.parseInt(r);

                                            Integer finalI = i3;
                                            Platform.runLater(()->{
                                                if(series3.getData().size()>400){
                                                    series3.getData().remove(0,10);
                                                }
                                                series3.getData().add(new XYChart.Data(finalI.toString(), value_real_value));
                                            });



                                        }
                                    }

                                    if(message_segment.contains("MDC_PULS_OXIM_PLETH")){
                                        String mod1 = message_segment.replace("{{","");
                                        String mod2 = mod1.replace("}}","");
                                        String mod3 = mod2.replace("Document","");
                                        String[] message = mod3.split("[,]");
                                        String reading = message[5];
                                        String reading2 = message[6];
                                        String[] process = reading.split("[|]");
                                        String reader = process[6];
                                        String[] values = reader.split("['^']");
                                        for (String r:values
                                        ) {
                                            //System.out.println(r);
                                            i1++;
                                            int value_real_value = Integer.parseInt(r);
                                            Integer finalI = i1;
                                            Platform.runLater(()->{
                                                if(series1.getData().size()>180){
                                                    series1.getData().remove(0);
                                                }
                                                series1.getData().add(new XYChart.Data(finalI.toString(), value_real_value));
                                            });

                                        }
                                    }

                                }

                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }
                    };
                    Thread t = new Thread(r);
                    t.start();

        }
    };


    EMIC_DASHBOARD_CONTENT(Stage PrimaryStage,String databasecondition) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(),15000);
        database = databasecondition;
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart.setMinWidth(700);
        lineChart.setMaxWidth(700);
        lineChart.setMaxHeight(400);
        lineChart.setAnimated(true);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        series1.setName("TTHOR");
        lineChart.getData().add(series1);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        lineChart1.setMinWidth(700);
        lineChart1.setMaxWidth(700);
        lineChart1.setMaxHeight(400);
        lineChart.setAnimated(true);
        xAxis1.setAnimated(false);
        yAxis2.setAnimated(false);
        xAxis1.setTickLabelsVisible(false);
        yAxis2.setTickLabelsVisible(false);
        series2.setName("Respiratory Rate");
        lineChart1.getData().add(series2);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        lineChart3.setMinWidth(1400);
        lineChart3.setMaxWidth(1400);
        lineChart3.setMaxHeight(400);
        lineChart.setAnimated(true);
        xAxis3.setAnimated(false);
        yAxis4.setAnimated(false);
        xAxis3.setTickLabelsVisible(false);
        yAxis4.setTickLabelsVisible(false);
        series3.setName("ECG");
        lineChart3.getData().addAll(series3);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fp.getChildren().addAll(lineChart,lineChart1,lineChart3);
        fp.setPadding(new Insets(30,10,10,10));
        fp.setMinWidth(1450);
        //THIS IS WHERE WE ADD ALL OF THE ELEMENTS TO THE SCREEN
        setCenter(fp);
        fp.setStyle("-fx-background-color:#c7c7c7");
        fp.setVgap(50);
        prefHeightProperty().bind(PrimaryStage.heightProperty());
        setStyle("-fx-background-color:#dddddd");
        getStylesheets().add("file:////home/katleho/EMIC/EMIC_UI/src/main/java/EMIC_UI/styles.css");

        requestTimer.schedule(simulator,2000,1500);



    }

}
