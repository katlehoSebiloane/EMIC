package EMIC_UI;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EMIC_TRENDS extends BorderPane {
    private BorderPane bp  = new BorderPane();
    private String SceneName = "Patient_Trends";
    private EMIC_NAVIGATION navigation = null;
    private TrendHeader hdr = null;
    private BottomStats bts = null;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String HeartRate;
    private String PulseRate;
    private String so2;
    private String Resp;
    private VBox Table_Container = new VBox();

    EMIC_TRENDS(Stage PrimaryStage) throws URISyntaxException, IOException {
      //  navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
        hdr = new TrendHeader();
        bts = new BottomStats(SceneName);
        socket = new Socket(InetAddress.getLocalHost(),15000);
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    printWriter = new PrintWriter(socket.getOutputStream(),true);
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    printWriter.println("LOG|D6_TEST_ROUND_TWO");
                    System.out.println(bufferedReader.readLine());
                    Scanner scanner = new Scanner(bufferedReader);
                    while(scanner.hasNextLine()){
                        String message_segment = scanner.nextLine();

                        if(message_segment.contains("MDC_ECG_HEART_RATE")){
                            String[] results =  message_segment.split("['$']");
                            String DATE =  results[0];
                            HeartRate = results[1];
                            PulseRate = results[2];
                            System.out.println("REAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDING: "+HeartRate);

                            String Performance = DATE;
                            String dates = Performance.substring(0, 4) + "/" + Performance.substring(4, 6) + "/" + Performance.substring(6, 8);
                            String times = Performance.substring(8, 10) + ":" + Performance.substring(10, 12) + ":" + Performance.substring(12, 14);
                                Table_Container.getChildren().add(new ReadingSlot(dates,HeartRate,times,"Heart Rate"));
                                Table_Container.getChildren().add(new ReadingSlot(dates,PulseRate,times,"Respiratory Rate"));



                        }

                        if(message_segment.contains("MDC_PULS_OXIM_SAT_O2")){
                            String[] results =  message_segment.split("['$']");
                            so2 = results[1];
                            Resp = results[2];
                            String DATE =  results[0];
                            String Performance =DATE;
                            System.out.println("REAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDING: "+so2);
                            String dates = Performance.substring(0, 4) + "/" + Performance.substring(4, 6) + "/" + Performance.substring(6, 8);
                            String times = Performance.substring(8, 10) + ":" + Performance.substring(10, 12) + ":" + Performance.substring(12, 14);
                                Table_Container.getChildren().add(new ReadingSlot(dates,so2,times,"Oxygen Saturation"));
                                Table_Container.getChildren().add(new ReadingSlot(dates,Resp,times,"Pulse Rate"));
                        }

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

        final Label label = new Label("Patient Vitals");
        label.setFont(new Font("Lato", 36));
        Text Emergency_Information = new Text();
        Emergency_Information.setText("Emergency ID: #KDehy^783jd            Paramedic: Dr Percy");
        Emergency_Information.setFont(Font.font("Lato", FontWeight.NORMAL, FontPosture.ITALIC,18));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        getStylesheets().add("file:////home/katleho/Desktop/EMIC/EMIC_UI/src/main/java/EMIC_UI/styles.css");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Table_Container.setSpacing(10);
        Table_Container.setPadding(new Insets(10));
        Table_Container.setStyle("-fx-background-color:#ffffff");
        ScrollPane s = new ScrollPane();
        s.setContent(Table_Container);
        s.setFitToWidth(true);
        s.setMaxHeight(954);
       // setLeft(navigation);
        setCenter(bp);
        bp.setCenter(s);
        bp.setTop(hdr);
        bp.setBottom(bts);

    }
}
