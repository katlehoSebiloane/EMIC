package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class EMERGENCY_TILE extends VBox {
    private VBox grey_box = new VBox();
    private Text ID = new Text("Emergency ID: #KZY5433226549!4S");
    private Text Date = new Text("Date of Emergency: 12 Dec 2020");
    private Text Hospital = new Text("Hospital: Mediclinic Trichardt");
    private Text Paramedic = new Text("Paramedic ID: SS45#@854t3yz");
    EMERGENCY_TILE(Stage PrimaryStage){
        grey_box.setPadding(new Insets(25));
        grey_box.setStyle("-fx-background-color:#2d2d2d;-fx-background-radius:0px");
        grey_box.setSpacing(10);
        ID.setFont(Font.font("Lato", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,16));
        ID.setFill(Color.WHITE);
        Hospital.setFill(Color.WHITE);
        Paramedic.setFill(Color.WHITE);
        Date.setFill(Color.WHITE);
        Date.setFont(Font.font("Lato", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,16));
        Hospital.setFont(Font.font("Lato", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,16));
        Paramedic.setFont(Font.font("Lato", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,16));
        grey_box.getChildren().addAll(ID,Date,Hospital,Paramedic);
        setStyle("-fx-background-color:#DC2929;-fx-background-radius:0px");
        setPadding(new Insets(0,30,0,0));
        getChildren().add(grey_box);

        setOnMouseClicked(e->{
            try {
                PrimaryStage.setScene(new Scene( new EMIC_TRENDS(PrimaryStage),1920,1050));
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });
    }


}
