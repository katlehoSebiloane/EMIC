package EMIC_SERVER_UI;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EMIC_VITAL extends HBox {
    private Text Title = new Text();
    private Text Reading = new Text();
    private Text Unit = new Text();

    EMIC_VITAL(String t,String r, String u){
        Title.setText(t+": ");
        Title.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, FontPosture.REGULAR,16));
        Title.setFill(Color.WHITE);

        Reading.setText(r+" ");
        Reading.setFont(Font.font("Segoe UI",FontWeight.BLACK,FontPosture.REGULAR,16));
        Reading.setFill(Color.WHITE);

        Unit.setText(u);
        Unit.setFont(Font.font("Segoe UI",FontWeight.LIGHT,FontPosture.REGULAR,16));
        Unit.setFill(Color.WHITE);


        setPadding(new Insets(25));
        setMaxWidth(450);
        setMinWidth(450);

        setStyle("-fx-background-color:#c4c4c4");
        getChildren().addAll(Title,Reading,Unit);

    }


}
