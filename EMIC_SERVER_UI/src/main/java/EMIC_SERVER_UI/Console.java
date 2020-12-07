package EMIC_SERVER_UI;


import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Console extends TextArea {
    Console(){
        setStyle("-fx-control-inner-background:#2d2d2d;-fx-background-radius:0px;-fx-text-fill: #00ff00;-fx-focus-color:#2d2d2d;-fx-background-color:#2d2d2d");
        setEditable(false);
        setFont(Font.font("Segoe UI",18));
        setBorder(new Border(new BorderStroke(Color.rgb(55,55,55), BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setWrapText(true);
        setMinWidth(200);
        setMinHeight(1020);
    }
}
