package EMIC_SERVER_UI;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class hl7_Interface extends BorderPane {
    public Navigation navigation= new Navigation();
    public Console console = new Console();
    public FlowPane fp = new FlowPane();
    private ScrollPane s = new ScrollPane();

    hl7_Interface(){
        fp.setHgap(10);
        fp.setVgap(10);
        fp.setPadding(new Insets(25));
        s.setContent(fp);
        s.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        fp.setMinWidth(1000);

        setLeft(navigation);
        setRight(console);
        setCenter(s);
    }
}
