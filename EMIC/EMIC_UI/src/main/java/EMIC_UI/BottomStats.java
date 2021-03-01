package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BottomStats extends BorderPane {
    private StringBuffer ER_SERVICE = new StringBuffer("ER24");
    private StringBuffer TRIAGE = new StringBuffer("YELLOW");
    private HBox Bottom_Stats = new HBox();
    private VBox Emergency_Services = new VBox();
    private VBox Emergency_Triage_Level = new VBox();
    private Text Emergency_Service_Content = new Text("Emergency Services: "+ER_SERVICE);
    private Text Emergency_Triage_Color = new Text("Triage: "+TRIAGE);

    BottomStats(String SceneName){
        setStyle("-fx-background-color:#2d2d2d");
        Bottom_Stats.setMinHeight(30);
        Emergency_Services.getChildren().add(Emergency_Service_Content);
        Emergency_Service_Content.setFill(Color.WHITE);
        Emergency_Service_Content.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,14));
        Emergency_Services.setPrefWidth(565);
        Emergency_Services.setPadding(new Insets(5,5,5,5));
        Emergency_Triage_Level.setStyle("-fx-background-color:yellow");
        Emergency_Triage_Level.setPadding(new Insets(5,5,5,5));
        Emergency_Triage_Level.setPrefWidth(565);
        Emergency_Triage_Color.setFont(Font.font("Segoe UI",FontWeight.EXTRA_LIGHT,FontPosture.ITALIC,14));
        Emergency_Triage_Level.getChildren().add(Emergency_Triage_Color);
        setLeft(Emergency_Services);
        setRight(Emergency_Triage_Level);

        if(SceneName.equals("Chat_Room")){
            Emergency_Triage_Level.setPrefWidth(760);
        }

    }
}
