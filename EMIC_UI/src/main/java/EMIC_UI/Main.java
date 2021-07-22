/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMIC_UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 27828
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EMIC_DASHBOARD ED = new EMIC_DASHBOARD(primaryStage);
        Scene s = new Scene(ED,1920,1040);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
