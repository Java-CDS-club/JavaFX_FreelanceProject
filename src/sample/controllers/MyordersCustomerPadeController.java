package sample.controllers;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class MyordersCustomerPadeController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private ListView<BorderPane> list;

    @FXML
    private JFXButton closeBut;


    @FXML
    private ComboBox<String> combo;

    @FXML
    private DatePicker pick;

    private String user;

    public void setUserr(String us) {
        user = us;
    }

    public String getUserr() {
        return user;
    }

    private int userID;

    public void setUserID(int user) {
        this.userID = user;
    }

    public int getUserID() {
        return userID;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        System.out.println(userID);
        ToolBar toolBar = new ToolBar();
        toolBar.onTopMoveAction(anchor);
        toolBar.onTopClickAction(anchor);


        closeBut.setOnAction(event -> {
            Stage stage = (Stage) closeBut.getScene().getWindow();

            stage.close();
        });


    }


    public void startList() throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.myOrdersCustomer(userID);
        System.out.println(userID);
        int i = 1;

        while (resultSet.next()) {

            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: #5b977a;");
               borderPane.setPadding(new Insets(10, 10, 10, 10));


            Label lab = new Label();
            lab.setStyle("-fx-font-size: 20px; -fx-font-color: #035644; ");
            lab.setText(resultSet.getString(6));
            lab.setMaxWidth(500);
            lab.setWrapText(true);


            JFXButton but = new JFXButton();
            but.setText("Удалить заказ");
            but.setStyle("-fx-background-color:#daa384;");

            try {
                int orderID = resultSet.getInt(1);

                but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(orderID);
                        //     Stage stage = (Stage) but.getScene().getWindow();

                        DatabaseHandler databaseHandler9 = new DatabaseHandler();
                        try {
                            databaseHandler9.deleteOrder(orderID);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        event.consume();
                    }
                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Label lab1 = new Label();
            lab1.setText(resultSet.getString(7));
            lab1.setStyle("-fx-font-size: 18px;-fx-text-fill: #035644;  ");
            lab1.setMaxWidth(500);
            lab1.setWrapText(true);
            Label status = new Label();
                        status.setText(resultSet.getString(4));
            status.setStyle("-fx-font-size: 18px; -fx-text-fill: #f1613e;-fx-padding: 10px;");

            status.setWrapText(true);

            borderPane.setTop(lab);
            borderPane.setLeft(lab1);
            borderPane.setRight(status);
            borderPane.setBottom(but);

            list.getItems().add(borderPane);
            i++;
        }
    }

    public void fun(){
        combo.setVisible(true);
        pick.setVisible(true);
        combo.getItems().add("Превосходно!");
        combo.getItems().add("Великолепно!");
        combo.getItems().add("Выше всяких похвал!");

    }
}
