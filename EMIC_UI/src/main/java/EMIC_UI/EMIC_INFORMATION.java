package EMIC_UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EMIC_INFORMATION extends VBox {

        private EMIC_INFORMATION_TILE Comments = null;
        private EMIC_INFORMATION_TILE Name_Surname = null;
        private EMIC_INFORMATION_TILE Gender = null;
        private EMIC_INFORMATION_TILE Age = null;
        private EMIC_INFORMATION_TILE Date = null;
        private EMIC_INFORMATION_TILE Address = null;
        private EMIC_INFORMATION_TILE Height_Weight = null;

    EMIC_INFORMATION(Stage PrimaryStage){

        ArrayList<String> Information = new ArrayList<String>();
        Information.add("Unconscious");
        Information.add("The Patient seems to be exhibiting signs of internal bleeding, having an low blood pressure and the pulse is fainting, this does not seem very good, his condition is deteriorating");

        Comments= new EMIC_INFORMATION_TILE(Information);
        setSpacing(10);
        setPadding(new Insets(10));
        Label Patient_Information_Label = new Label("Patient Registration Information");
        Patient_Information_Label.setFont(Font.font("Segoe UI", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR,26));
        Patient_Information_Label.setTextFill(Color.WHITE);
        Patient_Information_Label.setPadding(new Insets(50));
        getChildren().add(Patient_Information_Label);
        getChildren().add(Comments);
        setStyle("-fx-background-color:#DC2929;");
        setMaxWidth(630);
        prefHeightProperty().bind(PrimaryStage.heightProperty());

    }
}
