package sample.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;
import sample.animations.Shake;

import javax.swing.text.html.ImageView;


public class RegisterController {

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private RadioButton Customer;

    @FXML
    private RadioButton Employee;

    @FXML
    private Button SignUpButton;

    @FXML
    private Pane topPane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private Pane hoverpane;

    @FXML
    private Pane hoverpane1;

    public Stage getPreviousStage() {
        return previousStage;
    }

    public void setPreviousStage(Stage previousStage) {
        this.previousStage = previousStage;
    }

    Stage previousStage;

    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();
        Customer.setToggleGroup(group);
        Employee.setToggleGroup(group);
        Employee.setSelected(true);

        DatabaseHandler handler = new DatabaseHandler();
        SignUpButton.setOnAction(actionEvent -> {
            String action;
            if (Customer.isSelected()) action = "customer";
            else action = "employee";
            if (!LoginField.getText().isEmpty() && !PasswordField.getText().isEmpty() && !isUserExist()) {
                try {
                    handler.signUpUser(LoginField.getText(), PasswordField.getText(), action);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                previousStage.show();
                SignUpButton.getScene().getWindow().hide();
            } else {
                Shake shake = new Shake(SignUpButton);
                shake.playAnim();
            }
        });
//---------------------------------------toolbar
        ToolBar toolBar =  ToolbarSingle.getInstance();
        toolBar.setIconFieldButton(hoverpane1);
        toolBar.closeButton(hoverpane);
        toolBar.onTopClickAction(topPane);
        toolBar.onTopMoveAction(topPane);
    }


    private boolean isUserExist() {
        DatabaseHandler handler = new DatabaseHandler();
        User user = new User();
        user.setLogin(LoginField.getText());
        user.setPassword(PasswordField.getText());
        ResultSet resultSet = handler.getUser(user);
        int counter = 0;
        try {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter != 0) return true;
        else return false;
    }
}
