package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class exitButtonGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        try {
            GUIMain.get_gameEngine().get_gc().saveGameState();
        } catch (Exception e2) {
            e2.printStackTrace();
        }


        MenuItem source = (MenuItem) e.getSource();
        MenuButton parent = (MenuButton) source.getParentPopup().getOwnerNode();
        BorderPane bp = (BorderPane) parent.getParent();
        Scene sc = bp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createStartPage());

        //stage.show();

    }


}
