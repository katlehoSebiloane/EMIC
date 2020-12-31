package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Chat_Message_Recieved extends VBox {
    Chat_Message_Recieved(){
        setPadding(new Insets(25));
        setStyle("-fx-background-color:#DC2929;-fx-background-radius:10px");
        Text test = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud");
        test.setWrappingWidth(580);
        test.setFill(Color.WHITE);
        test.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,16));
        getChildren().add(test);
    }
}
