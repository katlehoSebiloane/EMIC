package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Chat_Pane extends BorderPane {
    private VBox id = new VBox();
    private Text Name = new Text("Katleho");
    private HBox Control_Panel = new HBox();
    private ImageView Send = new ImageView("file:///home/katleho/Documents/EMIC_UI/src/main/java/EMIC_UI/Send.png");
    private ImageView Voice = new ImageView("file:///home/katleho/Documents/EMIC_UI/src/main/java/EMIC_UI/Voice.png");
    private TextArea Input = new TextArea();
    private Chat_Scroller messages = new Chat_Scroller();
    Chat_Pane(){
        id.getChildren().add(Name);
        Control_Panel.setStyle("-fx-background-color:#AAAAAA");
        Control_Panel.setPadding(new Insets(25));
        Control_Panel.getChildren().addAll(Input,Send,Voice);
        Control_Panel.setSpacing(75);
        id.setStyle("-fx-background-color:#AAAAAA");
        id.setPadding(new Insets(25));
        Name.setFill(Color.WHITE);
        Name.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,24));
        Input.setPrefWidth(900);
        Input.setPrefHeight(40);
        Input.setStyle("-fx-background-radius:5000;-fx-background-color: transparent;");
        setTop(id);
        setBottom(Control_Panel);
        setCenter(messages);

    }

}
