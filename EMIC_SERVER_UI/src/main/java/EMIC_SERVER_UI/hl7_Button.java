package EMIC_SERVER_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class hl7_Button extends Button {
    hl7_Button(String Name){
        setStyle("-fx-background-color:#2d2d2d;-fx-background-radius:0px");
        setPadding(new Insets(15));
        Image icon = new Image("file:///home/katleho/Documents/emic/EMIC_SERVER_UI/src/main/java/EMIC_SERVER_UI/"+Name+".png");
        ImageView iconView = new ImageView();
        iconView.setFitWidth(20);
        iconView.setPreserveRatio(true);
        iconView.setImage(icon);
        setGraphic(iconView);
        Tooltip t = new Tooltip(Name);
        setTooltip(t);
        setMinWidth(90);

        setOnMouseEntered(e->{
            setStyle("-fx-background-color:#666666;-fx-background-radius:0px");
        });
        setOnMouseExited(e->{
            setStyle("-fx-background-color:#2d2d2d;-fx-background-radius:0px");
        });

    }
}
