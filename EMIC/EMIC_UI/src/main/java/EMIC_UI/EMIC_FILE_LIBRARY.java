package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
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
    private SplitPane sp = new SplitPane();

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
         EMERGENCY_TILE E19 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E20 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E21 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E22 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E23 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E24 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E25 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E26 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E27 = new EMERGENCY_TILE(PrimaryStage);
         EMERGENCY_TILE E28 = new EMERGENCY_TILE(PrimaryStage);
         Label Patient_Vitals = new Label("Patient Records:");
         Patient_Vitals.setFont(Font.font("Lato",24));
         Patient_Vitals.setPadding(new Insets(25));
         gp = new FlowPane();
         gp.getChildren().addAll(E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18,E19,E20,E21,E22,E23,E24,E25,E26,E27,E28);
         gp.setHgap(20);
         gp.setVgap(20);
         gp.setPadding(new Insets(50,5,50,50));

         s = new ScrollPane();
         s.setContent(gp);
         s.setFitToWidth(true);
         s.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
         s.setStyle("-fx-background:#dddddd");
         s.setMaxHeight(930);
         getStylesheets().add("file:////home/katleho/Desktop/EMIC/EMIC_UI/src/main/java/EMIC_UI/styles.css");
         setBottom(new BottomStats(""));
         VBox Container = new VBox();
         Container.setSpacing(10);
         Container.getChildren().addAll(Patient_Vitals,s);
         sp.getItems().addAll(Container,new file_trends());
         sp.setDividerPosition(0,0.82);
         setCenter(sp);
     }

}
