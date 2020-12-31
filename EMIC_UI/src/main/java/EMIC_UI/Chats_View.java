package EMIC_UI;

import javafx.scene.layout.BorderPane;

public class Chats_View extends BorderPane {
    private Header hdr = null;
    private BottomStats bts = null;
    private Chat_Pane cp = new Chat_Pane();
    private String SceneName = "Chat_Room";
    Chats_View(){
        hdr = new Header(SceneName);
        bts = new BottomStats(SceneName);
        setTop(hdr);
        setBottom(bts);
        setCenter(cp);
    }
}
