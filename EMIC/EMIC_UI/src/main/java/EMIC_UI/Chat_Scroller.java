package EMIC_UI;


import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Chat_Scroller extends ScrollPane {
    private VBox Messages = new VBox();

    Chat_Scroller(){
        Chat_Message_Sent c1 = new Chat_Message_Sent();
        Chat_Message_Recieved r1 = new Chat_Message_Recieved();
        Chat_Message_Sent c2 = new Chat_Message_Sent();
        Chat_Message_Recieved r2 = new Chat_Message_Recieved();
        Chat_Message_Sent c3 = new Chat_Message_Sent();
        Chat_Message_Recieved r3 = new Chat_Message_Recieved();
        setPadding(new Insets(50));
        Messages.getChildren().addAll(c1,r1,c2,r2,c3,r3);
        Messages.setSpacing(10);
        Messages.setMargin(c1,new Insets(0,500,0,0));
        Messages.setMargin(r1,new Insets(0,0,0,500));
        Messages.setMargin(c2,new Insets(0,500,0,0));
        Messages.setMargin(r2,new Insets(0,0,0,500));
        Messages.setMargin(c3,new Insets(0,500,0,0));
        Messages.setMargin(r3,new Insets(0,0,0,500));
        setContent(Messages);
    }
}
