package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.DatabaseHandler;

public class AboutPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea aboutText;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton closeBut;
    private int UserID;
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }



    @FXML
    void initialize() {
        closeBut.setOnAction(event -> {
            Stage stage = (Stage) closeBut.getScene().getWindow();

            stage.close();
        });


add.setOnAction(event -> {
    DatabaseHandler databaseHandler = new DatabaseHandler();
    try {
        databaseHandler.updateUser(getUserID(),aboutText.getText());
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Stage stage1 = (Stage)  add.getScene().getWindow();
    stage1.close();
});

    }

    public String setStatus(){
        String s = aboutText.getText();

        return s;
    }
}
