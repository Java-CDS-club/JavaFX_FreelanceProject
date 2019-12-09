package sample.controllers;

import java.io.File;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class FreelancerPageController {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changePhotoButton;

    @FXML
    private Button but;

    @FXML
    private Ellipse userImage1;

    @FXML
    private Label userLabel;

    @FXML
    private Ellipse userImage;

    @FXML
    private Label userName;

    @FXML
    private Label userName1;

    @FXML
    private Pane topPane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private Pane hoverpane;

    @FXML
    private Pane hoverpane1;

    //  private FileChooser fileChooser;
    //  private File file;
    private Image image;


    @FXML
    void initialize() {
        but.setOnAction(event -> {
//                    Stage stage = (Stage) changePhotoButton.getScene().getWindow();
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Open File");
                    File file = chooser.showOpenDialog(new Stage());
                    if (file != null) {
                        image = new Image(file.toURI().toString(), 300, 350, true, true);
                        userImage.setFill(new ImagePattern(image));
                        DatabaseHandler databaseHandler = new DatabaseHandler();
                    //    databaseHandler.imageToDatabase(file, user);

                        System.out.println(file.toURI().toString());
                        System.out.println(user.getLogin());
                        System.out.println(user.getPassword());
                        System.out.println(user.getAction());
                        System.out.println(user.getId());
                        System.out.println("jfgjdtjdtjfjdtj");
                    }

                });

        /*changePhotoButton.setOnAction(event -> {

            System.out.println(user.getLogin());
            //   ava.setStyle("-fx-background-radius: 30;");
            //  Stage stage = (Stage) changePhotoButton.getScene().getWindow();
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(new Stage());
            if (file != null) {
                image = new Image(file.toURI().toString(), 300, 350, true, true);
                userImage.setFill(new ImagePattern(image));
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.imageToDatabase(file,user);

                System.out.println(file.toURI().toString());
                System.out.println(user.getLogin());
                System.out.println(user.getPassword());
                System.out.println(user.getAction());
                System.out.println(user.getId());
            }

        });*/

                //----------------------------------------toolbar
                ToolBar toolBar = new ToolBar();
                toolBar.setIconFieldButton(hoverpane1);
                toolBar.closeButton(hoverpane);
                toolBar.onTopClickAction(topPane);
                toolBar.onTopMoveAction(topPane);

}
    @FXML
    void us() {
        userName.setText(user.getLogin());
        userLabel.setText(user.getLogin());
    }

}
