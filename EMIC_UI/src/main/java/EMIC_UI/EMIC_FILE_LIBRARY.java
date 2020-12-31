package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class EMIC_FILE_LIBRARY extends BorderPane {
    private EMIC_NAVIGATION navigation = null;
    private FlowPane gp = null;
    private String SceneName = "E_Patient_Report";
    private ScrollPane s = null;

     EMIC_FILE_LIBRARY(Stage PrimaryStage) throws URISyntaxException {
         navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
         setLeft(navigation);
         EMERGENCY_TILE E1 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E2 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E3 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E4 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E5 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E6 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E7 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E8 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E9 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E10 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E11 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E12 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E13 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E14 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E15 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E16 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E17 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E18 = new EMERGENCY_TILE(PrimaryStage);
         Label Patient_Vitals = new Label("Patient Records:");
         Patient_Vitals.setFont(Font.font("Segoe UI",24));
         Patient_Vitals.setPadding(new Insets(25));
         gp = new FlowPane();
         gp.getChildren().addAll(E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18);
         gp.setHgap(20);
         gp.setVgap(20);
         gp.setPadding(new Insets(50));
         s = new ScrollPane();
         s.setContent(gp);
         s.setFitToWidth(true);

         VBox Container = new VBox();
         Container.setSpacing(10);
         Container.getChildren().addAll(Patient_Vitals,s);
         setCenter(Container);
     }

}
