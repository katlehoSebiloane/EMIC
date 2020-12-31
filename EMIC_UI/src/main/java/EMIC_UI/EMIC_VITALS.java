package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EMIC_VITALS extends VBox {
        private Text Vital_Sign_Header = new Text();
        private Text Vital_Reading = new Text();
        private Text Vital_Unit_Measurement = new Text();
        private HBox ReadingUnit = new HBox();
    EMIC_VITALS(String name,String reading,String unit){
        Vital_Sign_Header.setText(name);
        Vital_Reading.setText(reading);
        Vital_Unit_Measurement.setText(unit);
        ReadingUnit.getChildren().addAll(Vital_Reading,Vital_Unit_Measurement);
        ReadingUnit.setSpacing(75);
        getChildren().addAll(Vital_Sign_Header,ReadingUnit);
        setStyle("-fx-background-color:#2d2d2d");
        setPadding(new Insets(25));
        Vital_Sign_Header.setFill(Color.WHITE);
        Vital_Sign_Header.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, FontPosture.ITALIC,18));
        Vital_Reading.setFill(Color.WHITE);
        Vital_Reading.setFont(Font.font("Segoe UI",FontWeight.BLACK,FontPosture.REGULAR,48));
        Vital_Unit_Measurement.setFill(Color.WHITE);
        Vital_Unit_Measurement.setFont(Font.font("Segoe UI",FontWeight.LIGHT,FontPosture.REGULAR,16));
        setPrefHeight(100);
        setPrefWidth(250);

    }

}
