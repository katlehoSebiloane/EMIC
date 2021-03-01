package EMIC_UI;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class EMIC_NAVIGATION extends VBox {

    private EMIC_BUTTON Activity = null;
    private EMIC_BUTTON Chat_Room = null;
    private EMIC_BUTTON Patient_Trends = null;
    private EMIC_BUTTON  E_Patient_Reports = null ;
    private EMIC_BUTTON Account_Details = null;
    private Image LOGO_IMG = new Image("file:////home/katleho/EMIC/EMIC_UI/src/main/java/EMIC_UI/Logo.png");
    private ImageView LOGO = new ImageView(LOGO_IMG);

    EMIC_NAVIGATION(Stage PrimaryStage,String SceneName){
        Activity = new EMIC_BUTTON("Activity",SceneName);
        Chat_Room = new EMIC_BUTTON("Chat_Room",SceneName);
        Patient_Trends = new EMIC_BUTTON("Patient_Trends",SceneName);
        E_Patient_Reports = new EMIC_BUTTON("E_Patient_Report",SceneName);
        Account_Details = new EMIC_BUTTON("Account_Details",SceneName);
        LOGO.setFitWidth(65);
        LOGO.setPreserveRatio(true);
        getChildren().add(LOGO);
        getChildren().add(Activity);
        getChildren().add(Patient_Trends);
        getChildren().add(E_Patient_Reports);


        setStyle("-fx-background-color:#DC2929");
        prefHeightProperty().bind(PrimaryStage.heightProperty());

        Chat_Room.setOnAction(e->{
            try {
                PrimaryStage.setScene(new Scene( new EMIC_CHAT_ROOM(PrimaryStage),1920,1050));
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });

        Activity.setOnAction(e->{
            try {
                PrimaryStage.setScene(new Scene( new EMIC_DASHBOARD(PrimaryStage),1920,1050));
            } catch (URISyntaxException | IOException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });

        Patient_Trends.setOnAction(e->{
            try {
                PrimaryStage.setScene(new Scene( new EMIC_TRENDS(PrimaryStage),1920,1050));
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });

        E_Patient_Reports.setOnAction(e->{
            try {
                PrimaryStage.setScene(new Scene( new EMIC_FILE_LIBRARY(PrimaryStage),1920,1050));
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });

    }






}
