package EMIC_UI;


import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.ArrayList;

public class EMIC_INFORMATION_TILE extends VBox {
    EMIC_INFORMATION_TILE(ArrayList<String> Information){
        // The Header of the information Tile
        Text Header = new Text("Paramedics Comments:");
        Header.setFill(Color.BLACK);
        Header.setWrappingWidth(591);
        Header.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR,24));

        //The Condition of the Patient
        Text Condition = new Text("Condition: "+ Information.get(0));
        Condition.setFill(Color.BLACK);
        Condition.setFont(Font.font("Segoe UI", FontWeight.BLACK, FontPosture.ITALIC,16));
        Condition.setWrappingWidth(591);

        //The Notes Label Actually
        Text Notes = new Text("Notes:");
        Notes.setFill(Color.BLACK);
        Notes.setFont(Font.font("Segoe UI", FontWeight.LIGHT, FontPosture.REGULAR,16));
        Notes.setWrappingWidth(591);

        //The Paramedics Comment on the patient
        Text Comment = new Text(Information.get(1));
        Comment.setFill(Color.BLACK);
        Comment.setFont(Font.font("Segoe UI", FontWeight.LIGHT, FontPosture.REGULAR,16));
        Comment.setWrappingWidth(591);


        // The VBox Containing the things
        getChildren().add(Header);
        getChildren().add(Condition);
        getChildren().add(Notes);
        getChildren().add(Comment);
        setSpacing(10);
        setStyle("-fx-background-radius:10px;-fx-background-color:#fff");
        setPadding(new Insets(15));

    }
}
