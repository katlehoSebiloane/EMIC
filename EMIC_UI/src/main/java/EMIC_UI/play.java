package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class play extends BorderPane {
    public EMIC_VITALS EmV1;
    public EMIC_VITALS EmV2;
    public EMIC_VITALS EmV3;
    public EMIC_VITALS EmV4;
    public EMIC_VITALS EmV5;
    public EMIC_VITALS EmV6;
    private VBox sign = new VBox();
    private javafx.scene.control.Label Vitals_Label = new Label("Vital Signs");
    play(Stage PrimaryStage, String monitor) throws IOException {
        EmV1 = new EMIC_VITALS("Heart Rate: ","0","bpm");
        EmV2 = new EMIC_VITALS("Pulse Rate: ","0","bpm");
        EmV3 = new EMIC_VITALS("Respiration Rate: ","0","rpm");
        EmV4 = new EMIC_VITALS("Sp02: ","0","%");
        EmV5 = new EMIC_VITALS("ETCO2: ","0","%");
        EmV6 = new EMIC_VITALS("Blood Pressure: ","0/0","mmhg");
        sign.setStyle("-fx-background-color:#efefef");
        sign.getChildren().addAll(Vitals_Label,EmV1,EmV2,EmV3,EmV4,EmV5,EmV6);
        sign.setSpacing(10);
        sign.setMaxWidth(390);
        setRight(sign);
        sign.setPadding(new Insets(0,15,15,15));
        Vitals_Label.setPadding(new Insets(15));
        Vitals_Label.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,24));
        Vitals_Label.setTextFill(Color.web("#777777"));
        Vitals_Label.setStyle("-fx-background-color:#efefef");
        Vitals_Label.minWidthProperty().bind(sign.widthProperty().subtract(15));
        EMIC_DASHBOARD_CONTENT content = new EMIC_DASHBOARD_CONTENT(PrimaryStage,monitor);

        Timer tim = new Timer();
        TimerTask timTak = new TimerTask() {
            @Override
            public void run() {
                EmV1.Vital_Reading.setText(content.HeartRate);
                EmV2.Vital_Reading.setText(content.PulseRate);
                EmV3.Vital_Reading.setText(content.resp);
                EmV4.Vital_Reading.setText(content.so2);
            }
        };

        tim.schedule(timTak,500,1100);
        BottomStats Bottom_Stats = new BottomStats("Activity");
        setBottom(Bottom_Stats);
        setCenter(content);
        //setMaxHeight(956);
        setTop(new Header());
    }
}
