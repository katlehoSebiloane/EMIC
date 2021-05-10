package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReadingSlot extends BorderPane {
    int MAX_HEIGHT = 45;
    String background = "#eeeeee";
    Label Date;
    Label Time;
    Label Reading;
    Label Names;
    ReadingSlot(String Dates,String Readings,String Times,String Name){
        Date = new Label(Dates);
        Time = new Label(Times);
        Reading = new Label(Readings);
        Names = new Label(Name);

        BorderPane bp = new BorderPane();
        bp.setLeft(Names);
        bp.setRight(Date);
        setLeft(bp);
        setCenter(Reading);
        setRight(Time);
        setStyle("-fx-background-color:"+background);
        setPadding(new Insets(15));

        Names.setFont(Font.font("Lato", FontWeight.BOLD,18));
        Names.setTextFill(Color.web("#555555"));
        Names.setMinWidth(300);
        Date.setFont(Font.font("Lato", FontWeight.BOLD,18));
        Date.setTextFill(Color.web("#555555"));
        Date.setMinWidth(300);
        Time.setFont(Font.font("Lato", FontWeight.BOLD,18));
        Time.setTextFill(Color.web("#555555"));
        Time.setMinWidth(300);
        Reading.setFont(Font.font("Lato", FontWeight.BOLD,24));
        Reading.setTextFill(Color.web("#555555"));
        Reading.setMinWidth(300);




    }
}
