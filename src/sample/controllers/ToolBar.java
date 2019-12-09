package sample.controllers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ToolBar {

    private double xOffset ;
    private double yOffset ;

    public void setIconFieldButton(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

             Stage stage = (Stage) node.getScene().getWindow();

                stage.setIconified(true);
                event.consume();
            }
        });
    }

    public void closeButton(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) node.getScene().getWindow();

                stage.close();
                event.consume();
            }
        });
    }


    public void onTopClickAction(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) node.getScene().getWindow();

              xOffset = stage.getX() - event.getScreenX();
              yOffset = stage.getY() - event.getScreenY();
                event.consume();
            }
        });
    }

    public void onTopMoveAction(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) node.getScene().getWindow();

                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
              //   event.consume();
            }
        });
    }
}
