package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DatabaseHandler;
import sample.User;
import sample.animations.Shake;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller {



    @FXML
    private Label freeLabel;

    @FXML
    private Button SignInButton1;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Pane topPane;

    @FXML
    private Pane hoverpane;

    @FXML
    private Pane hoverpane1;

    @FXML
    private JFXButton SignInButton;


    @FXML
    void initialize() {


        SignInButton.setOnAction(actionEvent -> {
        //    if(SignInButton.isHover()) SignInButton.setStyle("-fx-background-color:#B3B3B3");
          //  if(SignInButton.isFocused()) SignInButton.setStyle("-fx-background-color:#B3B3B3");

            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();

            if (!login.equals("") && !password.equals("")) {
                try {
                    loginUser(login, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Shake userLoginAnimation = new Shake(LoginField);
                Shake userPasswordAnimation = new Shake(PasswordField);
                userLoginAnimation.playAnim();
                userPasswordAnimation.playAnim();

            }
        });

        SignUpButton.setOnAction(actionEvent -> {
            SignUpButton.getScene().getWindow().hide();

            Stage thisStage =  (Stage) SignUpButton.getScene().getWindow();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/register.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RegisterController controller;
            controller=   loader.getController();
            controller.setPreviousStage(thisStage);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root, 1280, 750));
            stage.showAndWait();
        });


//---------------------------------------toolbar
        ToolBar toolBar = ToolbarSingle.getInstance();
        toolBar.setIconFieldButton(hoverpane1);
        toolBar.closeButton(hoverpane);
        toolBar.onTopClickAction(topPane);
        toolBar.onTopMoveAction(topPane);

    }


    private void loginUser(String login, String password) throws SQLException, IOException, ClassNotFoundException {
        DatabaseHandler handler = new DatabaseHandler();
        User user = new User();
        user.setLogin(LoginField.getText());
        user.setPassword(PasswordField.getText());
        handler.getUser(user);
        ResultSet resultSet = handler.getUser(user);

        int counter = 0;
        try {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter == 1) {
            showMainPageScreen(user);
        } else {
            Shake userLoginAnimation = new Shake(LoginField);
            Shake userPasswordAnimation = new Shake(PasswordField);
            userLoginAnimation.playAnim();
            userPasswordAnimation.playAnim();
        }
    }

    private void showMainPageScreen(User user) throws SQLException, IOException, ClassNotFoundException {
        Stage stage1 = (Stage)  SignUpButton.getScene().getWindow();
        stage1.close();
      //  SignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getUser(user);

        int counter = 0;
        try {
            while (resultSet.next()) {
           //     counter++;
               // if (counter >= 1) {
                    user.setId( resultSet.getInt(1));
                    user.setAction( resultSet.getString(4));
                    if (!(resultSet.getBlob(5)==null)) user.setImage(true);
              //  }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        loader.setLocation(getClass().getResource("/sample/view/main.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainPageController controller;
        controller = loader.getController();



        controller.setUser(user);
        controller.us();
       if (user.getImage()==true) {


       controller.im(databaseHandler.imageFromDatabase(user));
       }


        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 1280, 750));
      //  stage.setScene(new Scene(root));
        stage.showAndWait();


    }
}

