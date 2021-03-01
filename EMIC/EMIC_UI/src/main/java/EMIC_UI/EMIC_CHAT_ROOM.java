package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class EMIC_CHAT_ROOM extends GridPane {
    private String SceneName = "Chat_Room";
    private VBox Vitals_Side_Bar = new VBox();
    private ArrayList<String> Vital = new ArrayList<String>();
    private EMIC_VITALS EmV1 = null;
    private EMIC_VITALS EmV2 = null;
    private EMIC_VITALS EmV3 = null;
    private EMIC_VITALS EmV4 = null;
    private EMIC_VITALS EmV5 = null;
    private EMIC_VITALS EmV6 = null;
    private Label Patient_Vitals = new Label("Patient Vitals:");
    private Chats_View chats_view= new Chats_View();

    EMIC_CHAT_ROOM(Stage PrimaryStage) throws URISyntaxException {
        Patient_Vitals.setFont(Font.font("Segoe UI", FontWeight.THIN, FontPosture.REGULAR,24));
        Patient_Vitals.setTextFill(Color.BLACK);
        Patient_Vitals.setPadding(new Insets(0,0,0,100));
        Vitals_Side_Bar.getChildren().add(Patient_Vitals);
        Vitals_Side_Bar.setPadding(new Insets(50));
        Vitals_Side_Bar.setStyle("-fx-background-color:#EAEAEA");
        Vitals_Side_Bar.setSpacing(10);
        EmV1 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        EmV2 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        EmV3 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        EmV4 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        EmV5 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        EmV6 = new EMIC_VITALS("Heart Rate: ","60","bpm");
        Vitals_Side_Bar.getChildren().addAll(EmV1,EmV2,EmV3,EmV4,EmV5,EmV6);

        EMIC_NAVIGATION navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
        add(Vitals_Side_Bar,2,0);
        add(chats_view,1,0);
        add(navigation,0,0);
    }
}
