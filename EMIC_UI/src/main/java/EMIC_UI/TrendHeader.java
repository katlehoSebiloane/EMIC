package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TrendHeader extends BorderPane {
    private StringBuffer STATE = new StringBuffer("No Emergency");
    private StringBuffer TIME = new StringBuffer("5 mins");
    private HBox HeaderTop = new HBox();
    private HBox HeaderLeft = new HBox();
    private HBox HeaderRight = new HBox();
    private Label Emergency_Dashboard = new Label("Patient Trend Reports Status: ");
    private Text Emergency_State = new Text(STATE.toString());
    private Label ETA = new Label("Time Recorded: ");
    private Text Time = new Text(TIME.toString());
    TrendHeader(){
        setStyle("-fx-background-color:#ffffff");
        HeaderLeft.getChildren().add(Emergency_Dashboard);
        HeaderLeft.getChildren().add(Emergency_State);
        HeaderRight.getChildren().add(ETA);
        HeaderRight.getChildren().add(Time);
        setLeft(HeaderLeft);
        setRight(HeaderRight);
        setPadding(new Insets(19));



        Emergency_State.setText("Ongoing....");
        if(Emergency_State.getText().equals("Ongoing....")){
            Emergency_State.setFill(Color.RED);
        }else{
            Emergency_State.setFill(Color.BLACK);
        }
        Emergency_State.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR,18));
        Emergency_Dashboard.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,18));
        ETA.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,18));
        Time.setFont(Font.font("Segoe UI",FontWeight.NORMAL,FontPosture.REGULAR,18));
        Time.setText("7 mins");
        Time.setFill(Color.RED);



    }
}
