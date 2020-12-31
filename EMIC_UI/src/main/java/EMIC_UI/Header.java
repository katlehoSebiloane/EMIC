package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Header extends HBox {
    private StringBuffer STATE = new StringBuffer("No Emergency");
    private StringBuffer TIME = new StringBuffer("5 mins");
    private HBox HeaderTop = new HBox();
    private HBox HeaderLeft = new HBox();
    private HBox HeaderRight = new HBox();
    private Label Emergency_Dashboard = new Label("Emergency Dashboard: ");
    private Text Emergency_State = new Text(STATE.toString());
    private Label ETA = new Label("ETA: ");
    private Text Time = new Text(TIME.toString());
    Header(String Scenename){
        HeaderLeft.getChildren().add(Emergency_Dashboard);
        HeaderLeft.getChildren().add(Emergency_State);
        HeaderRight.getChildren().add(ETA);
        HeaderRight.getChildren().add(Time);
        getChildren().add(HeaderLeft);
        getChildren().add(HeaderRight);
        setPadding(new Insets(25));
        setSpacing(320);

        if(Scenename.equals("Chat_Room")){
            setStyle("-fx-background-color:#2d2d2d");
            Time.setFill(Color.WHITE);
            ETA.setTextFill(Color.WHITE);
            Emergency_Dashboard.setTextFill(Color.WHITE);
            Emergency_State.setFill(Color.WHITE);
            setSpacing(580);
        }

        if(Scenename.equals("Patient_Trends")){
            setStyle("-fx-background-color:#2d2d2d");
            Time.setFill(Color.WHITE);
            ETA.setTextFill(Color.WHITE);
            Emergency_Dashboard.setTextFill(Color.WHITE);
            Emergency_State.setFill(Color.WHITE);
            setSpacing(1025);
        }

        Emergency_State.setText("EMERGENCY DETECTED!");
        if(Emergency_State.getText().equals("EMERGENCY DETECTED!")){
            Emergency_State.setFill(Color.RED);
        }else{
            Emergency_State.setFill(Color.BLACK);
        }
        Emergency_State.setFont(Font.font("Segoe UI", FontWeight.BLACK, FontPosture.ITALIC,26));
        Emergency_Dashboard.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,26));
        ETA.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,26));
        Time.setFont(Font.font("Segoe UI",FontWeight.BLACK,FontPosture.ITALIC,26));
        Time.setText("7 mins");



    }
}
