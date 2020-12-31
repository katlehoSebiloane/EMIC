package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URISyntaxException;


public class EMIC_DASHBOARD extends BorderPane {
        private String SceneName = "Activity";
        public EMIC_VITALS EmV1;
        public EMIC_VITALS EmV2;
        public EMIC_VITALS EmV3;
        public EMIC_VITALS EmV4;
        public EMIC_VITALS EmV5;
        public EMIC_VITALS EmV6;
        private VBox sign = new VBox();
        private BorderPane play = new BorderPane();
        private javafx.scene.control.Label Vitals_Label = new Label("Vital Signs");
        EMIC_DASHBOARD(Stage PrimaryStage) throws URISyntaxException {
                EmV1 = new EMIC_VITALS("Heart Rate: ","60","bpm");
                EmV2 = new EMIC_VITALS("Pulse Rate: ","60","bpm");
                EmV3 = new EMIC_VITALS("Respiration Rate: ","60","rpm");
                EmV4 = new EMIC_VITALS("Oxygen Saturation: ","60","%");
                EmV5 = new EMIC_VITALS("ETCO2: ","60","%");
                EmV6 = new EMIC_VITALS("Blood Pressure: ","80/120","mmhg");
                sign.setStyle("-fx-background-color:#ffffff");
                sign.getChildren().addAll(Vitals_Label,EmV1,EmV2,EmV3,EmV4,EmV5,EmV6);
                sign.setSpacing(10);
                sign.setPadding(new Insets(0));
                Vitals_Label.setPadding(new Insets(35));
                Vitals_Label.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,24));
                Vitals_Label.setTextFill(Color.BLACK);
                EMIC_NAVIGATION navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
                EMIC_DASHBOARD_CONTENT content = new EMIC_DASHBOARD_CONTENT(PrimaryStage);
                setLeft(navigation);
                play.setRight(sign);
                BottomStats Bottom_Stats = new BottomStats("Activity");
                play.setBottom(Bottom_Stats);
                play.setCenter(content);
                setLeft(navigation);
                setCenter(play);
            }
}
