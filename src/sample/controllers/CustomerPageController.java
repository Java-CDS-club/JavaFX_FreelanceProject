

package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import sample.User;

public class CustomerPageController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Ellipse userImage1;

    @FXML
    private Label userLabel;

    @FXML
    private Pane topPane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private Pane hoverpane;

    @FXML
    private Pane hoverpane1;

    @FXML
    private Ellipse userImage;

    @FXML
    private Label userName;

    @FXML
    private Button but;

    @FXML
    private TextField LoginField;

    @FXML
    private javafx.scene.control.PasswordField PasswordField;

    User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @FXML
    void initialize() {
//------------------------------------------------------------toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.setIconFieldButton(hoverpane1);
        toolBar.closeButton(hoverpane);
        toolBar.onTopClickAction(topPane);
        toolBar.onTopMoveAction(topPane);
    }

    void us() {
        userName.setText(user.getLogin());
        userLabel.setText(user.getLogin());
    }
}
