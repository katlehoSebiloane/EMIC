package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class EMIC_BUTTON extends Button {

    EMIC_BUTTON(String Name,String SceneName) {
        if(Name.equals(SceneName)){
            setStyle("-fx-background-color:#2d2d2d;-fx-text-color:#ffffff;-fx-background-radius:0px;");
        }else{
            setStyle("-fx-background-color:#DC2929;-fx-text-color:#ffffff;-fx-background-radius:0px;");
        }

        setPadding(new Insets(5,22,5,22));
        setPrefHeight(60);
        setPrefWidth(50);
        setFont(Font.font("Segoe UI",FontWeight.BOLD,FontPosture.ITALIC,12));
        setTextFill(Color.WHITE);
        Image img = new Image("file:////home/katleho/EMIC/EMIC_UI/src/main/java/EMIC_UI/"+Name+".png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        view.setPreserveRatio(true);
        setGraphic(view);

        setOnMouseEntered(e->{
            setStyle("-fx-background-color:#2d2d2d;-fx-text-color:#ffffff;-fx-background-radius:0px;");
            if(Name == SceneName){
                setStyle("-fx-background-color:#2d2d2d;-fx-text-color:#ffffff;-fx-background-radius:0px;");
            }
        });
        setOnMouseExited(e->{
            setStyle("-fx-background-color:#DC2929;-fx-text-color:#ffffff;-fx-background-radius:0px;");
            if(Name == SceneName){
                setStyle("-fx-background-color:#2d2d2d;-fx-text-color:#ffffff;-fx-background-radius:0px;");
            }
        });
    }
}
