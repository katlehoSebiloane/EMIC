package EMIC_SERVER_UI;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Navigation extends VBox {
    private HBox Logo_Container = new HBox();
    private Image Logo = new Image("file:////home/katleho/Desktop/EMIC/EMIC_SERVER_UI/src/main/java/EMIC_SERVER_UI/1.png");
    private ImageView Logo_View= new ImageView(Logo);
    public hl7_Button Read = new hl7_Button("Read");
    public hl7_Button Parse = new hl7_Button("Parse");
    public hl7_Button Test = new hl7_Button("Test");
    public hl7_Button Restart = new hl7_Button("Restart");
    public hl7_Button Close = new hl7_Button("Shutdown");
    public hl7_Button Connect = new hl7_Button("Connect");
    public hl7_Button Simulate = new hl7_Button("Simulate");
    public TextField Destination = new TextField();
    private HBox Des_Container = new HBox();
    Navigation(){
        Des_Container.getChildren().add(Destination);
        Destination.setMaxWidth(60);
        Des_Container.setPadding(new Insets(15));
        Logo_Container.getChildren().add(Logo_View);
        Tooltip.install(Logo_Container,new Tooltip("Home"));
        Logo_Container.setStyle("-fx-background-color:#2d2d2d");
        Logo_View.setFitWidth(90);
        Logo_View.setPreserveRatio(true);
        setStyle("-fx-background-color:#2d2d2d");
        setSpacing(10);
        getChildren().addAll(Logo_Container,Connect,Test,Read,Simulate,Restart,Close,Des_Container);

    }
}
