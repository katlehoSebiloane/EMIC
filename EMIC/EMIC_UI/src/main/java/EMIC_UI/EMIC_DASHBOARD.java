package EMIC_UI;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;


public class EMIC_DASHBOARD extends BorderPane {
        private String SceneName = "Activity";
        private TabPane shower = new TabPane();
        private Tab t = new Tab("Monitor 1");
        private Tab t2 = new Tab("Monitor 2");
        private Tab t3 = new Tab("Monitor 3");
        private Tab t4 = new Tab("Monitor 4");
        EMIC_DASHBOARD(Stage PrimaryStage) throws URISyntaxException, IOException {
                shower.getTabs().addAll(t,t2,t3,t4);
                EMIC_NAVIGATION navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
                setLeft(navigation);
                setLeft(navigation);
                setCenter(shower);
                t.setContent(new play(PrimaryStage,"D3_TEST_ROUND_TWO"));
                t2.setContent(new play(PrimaryStage,"D6_TEST_ROUND_TWO"));
                t3.setContent(new play(PrimaryStage,"D3_TEST_ROUND_TWO"));
                t4.setContent(new play(PrimaryStage,"D6_TEST_ROUND_TWO"));
                getStylesheets().add("file:////home/katleho/EMIC/EMIC_UI/src/main/java/EMIC_UI/styles.css");

        }
}
