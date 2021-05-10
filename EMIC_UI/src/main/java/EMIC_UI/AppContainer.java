package EMIC_UI;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AppContainer extends BorderPane {


    public AppContainer(Stage primaryStage, EMIC_DASHBOARD ed, EMIC_TRENDS et) {
        EMIC_NAVIGATION NAV = new EMIC_NAVIGATION(primaryStage,"Activity");
        setLeft(NAV);
        setCenter(ed);
        NAV.Activity.setOnAction(e->{
            setCenter(ed);
        });

        NAV.Patient_Trends.setOnAction(e->{
            setCenter(et);
        });
    }
}
