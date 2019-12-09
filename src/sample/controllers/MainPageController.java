package sample.controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observer;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DatabaseHandler;
import sample.User;
import sample.animations.Shake;

public class MainPageController {

    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private AnchorPane layoutt;

    @FXML
    private AnchorPane common;

    @FXML
    private Ellipse userImage2;

    @FXML
    private Label userName2;

    @FXML
    private Label listLabel;
    @FXML
    private Button aboutMeBut;
    @FXML
    private javafx.scene.text.Text AboutMeLabel;
    @FXML
    private Button but1;

    @FXML
    private Button myordersButton;

    @FXML
    private Ellipse userImage;

    @FXML
    private Label userLabel;

    @FXML
    private JFXButton MyPageBut;

    @FXML
    private JFXButton workBut;

    @FXML
    private JFXButton freelancerBut;

    @FXML
    private JFXButton mainBut;
    @FXML
    private Pane aboutProg;
    @FXML
    private JFXButton aboutBut;

    @FXML
    private Slider price;

    @FXML
    private ProgressBar progressBar;



    @FXML
    private Button but;

    @FXML
    private Pane topPane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private Pane hoverpane;

    @FXML
    private Pane hoverpane1;

    @FXML
    private Pane freelancer;
    @FXML
    private ImageView leave;

    @FXML
    private ImageView leave1;
    @FXML
    private Label userName211;

    @FXML
    private Pane customer;

    @FXML
    private TextField Title;

    @FXML
    private Label userName21;

    @FXML
    private TextField Text;

    @FXML
    private TextField price1;

    @FXML
    private Button publish1;

    @FXML
    private ListView<BorderPane> listt1;
    @FXML
    private ListView<BorderPane> listt;

    @FXML
    private ListView<BorderPane> freel;
    private Image image;

    private boolean myPageFlag = false;
    private boolean workFlag = false;
    private boolean freelFlag = false;

    public void setMyPageFlag(Boolean flag) {
        myPageFlag = flag;
    }

    public void setFreelFlag(Boolean flag) {
        freelFlag = flag;
    }

    public void setWorkFlag(Boolean flag) {
        workFlag = flag;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        aboutMeBut.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/aboutPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AboutPageController controller;
            controller = loader.getController();
            controller.setUserID(user.getId());

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root, 816, 500));
            stage.showAndWait();
            AboutMeLabel.setText( controller.setStatus());
            if (user.getAction().equals("customer")) {


            }

        });

        least();

        aboutBut.setOnAction(event -> {
            if (common.isVisible()) {
                common.setVisible(false);
                freelancer.setVisible(false);
                customer.setVisible(false);

            }

            listLabel.setVisible(false);
            if (listt1.isVisible()) listt1.setVisible(false);
            freel.setVisible(false);
            listt.setVisible(false);
            aboutProg.setVisible(true);
        });
        mainBut.setOnAction(event -> {
            if (common.isVisible()) {
                common.setVisible(false);
                freelancer.setVisible(false);
                customer.setVisible(false);
            }
            listLabel.setVisible(true);
            listLabel.setText("Опубликованные заказы");
            aboutProg.setVisible(false);
            if (listt1.isVisible()) listt1.setVisible(false);
            freel.setVisible(false);
            listt.setVisible(true);


        });


        myordersButton.setOnAction(event -> {

            System.out.println(user.getId());
            //  Stage thisStage =  (Stage) myordersButton.getScene().getWindow();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/MyordersCustomerPade.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MyordersCustomerPadeController controller;
            controller = loader.getController();
            controller.setUserr(user.getAction());
            System.out.println(user.getId());
            controller.setUserID(user.getId());
            if (user.getAction().equals("customer")) {
                try {
                    controller.startList();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                controller.fun();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root, 816, 500));
            stage.showAndWait();

            if (user.getAction().equals("customer")) {


            }

        });

        price.setMax(1000);
        price.setMin(0);
        price.valueProperty().addListener((ov, old_val, new_val) -> {
            progressBar.setProgress(new_val.doubleValue() / 1000);

        });

        workBut.setOnAction(event -> {

            listt.setVisible(false);
            listt1.setVisible(true);
            freel.setVisible(false);
listLabel.setVisible(true);
listLabel.setText("Опубликованные заказы");
            aboutProg.setVisible(false);
            if (common.isVisible()) {
                common.setVisible(false);
                if (user.getAction().equals("customer")) customer.setVisible(false);
                else freelancer.setVisible(false);
            }
            try {
                workFunc();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });



        publish1.setOnAction(event -> {
            if (!price1.getText().isEmpty() && !Title.getText().isEmpty() && !Text.getText().isEmpty()) {

                int price5 = Integer.parseInt(price1.getText());

                DatabaseHandler databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.setNewOrder(user.getId(), price5, Title.getText(), Text.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(price5);

                Text.clear();
                Title.clear();
                price1.clear();
            } else {
                Shake shake = new Shake(Title);
                shake.playAnim();
                Shake shake1 = new Shake(Text);
                shake1.playAnim();
                Shake shake2 = new Shake(price1);
                shake2.playAnim();
            }
        });

        but1.setOnAction(event -> {


            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(new Stage());
            if (file != null) {
                image = new Image(file.toURI().toString(), 300, 350, true, true);
                userImage.setFill(new ImagePattern(image));
                userImage2.setFill(new ImagePattern(image));
                DatabaseHandler databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.imageToDatabase(file, user);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(file.toURI().toString());
                System.out.println(user.getLogin());
                System.out.println(user.getPassword());
                System.out.println(user.getAction());
                System.out.println(user.getId());
                System.out.println("jfgjdtjdtjfjdtj");
            }

        });
        MyPageBut.setOnAction(event -> {
            listt.setVisible(false);
            listLabel.setVisible(false);
            leave.setVisible(false);
            leave1.setVisible(false);
            aboutProg.setVisible(false);
            common.setVisible(true);

            freel.setVisible(false);
            listt1.setVisible(false);
            if (listt1.isVisible()) listt1.setVisible(false);
            if (user.getAction().equals("customer")) {
                customer.setVisible(true);
            } else freelancer.setVisible(true);

        });

        freelancerBut.setOnAction(event -> {
            if (common.isVisible()) {
                common.setVisible(false);
                freelancer.setVisible(false);
                customer.setVisible(false);
            }
            listLabel.setVisible(true);
            listLabel.setText("Зарегистрированные фрилансеры");
            aboutProg.setVisible(false);
            if (listt1.isVisible()) listt1.setVisible(false);
            if (listt.isVisible()) listt.setVisible(false);

            freel.setVisible(true);
            if (freelFlag == false) {
                DatabaseHandler databaseHandler7 = new DatabaseHandler();
                ResultSet resultSet = null;
                try {
                    resultSet = databaseHandler7.getfreelancer();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    BorderPane borderPane = new BorderPane();
                    borderPane.setStyle("-fx-background-color: #5b977a;");
                    //   borderPane.setPadding(new Insets(15, 20, 10, 10));
                    borderPane.setPadding(new Insets(10, 10, 10, 10));

                    HBox hhh = new HBox();
                    hhh.setStyle("-fx-background-color: #5b977a;");
                    hhh.setSpacing(10);
                    //   hui.setPrefSize(200,100);
                    Label lab = new Label();
                    lab.setStyle("-fx-font-size: 20px; -fx-text-fill:  #fff6e3; -fx-font-weight: bold;");
                    try {
                        lab.setText(resultSet.getString(2));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                   // lab.setMaxWidth(600);
                    lab.setWrapText(true);

                    Ellipse ellipse = new Ellipse();
                    ellipse.setRadiusX(30);
                    ellipse.setRadiusY(30);
Label descr = new Label();
                    try {
                        descr.setText(resultSet.getString(6));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    descr.setStyle("-fx-font-size: 18px; -fx-text-fill:  #fff6e3; ");
                    descr.setMaxWidth(600);
                    descr.setWrapText(true);
                    User us = new User();
                    try {
                        us.setId(resultSet.getInt(1));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (!(resultSet.getBlob(5) == null)) us.setImage(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (us.getImage() == true) {
                        try {
                            ellipse.setFill(new ImagePattern(databaseHandler7.imageFromDatabase(us)));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else ellipse.setFill(Color.AQUA);
                    hhh.getChildren().add(ellipse);
                    hhh.getChildren().add(lab);
                    hhh.getChildren().add(descr);


                    borderPane.setTop(hhh);


                    freel.getItems().add(borderPane);

                }

                setFreelFlag(true);
            }
        });

//------------------------------------------------------------toolbar
        ToolBar toolBar =  ToolbarSingle.getInstance();
        toolBar.setIconFieldButton(hoverpane1);
        toolBar.closeButton(hoverpane);
        toolBar.onTopClickAction(topPane);
        toolBar.onTopMoveAction(topPane);
    }

    @FXML
    void us() {
        userLabel.setText(user.getLogin());
        userName2.setText(user.getLogin());
    }

    @FXML
    void im(Image image) {
        userImage.setFill(new ImagePattern(image));
        userImage2.setFill(new ImagePattern(image));
    }

    private void startUserPage(Node node) {

        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/freelancerPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FreelancerPageController controller;
        controller = loader.getController();

        controller.setUser(user);
        controller.us();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1280, 750));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.showAndWait();

    }

    private void startCustomerPage(Node node) {

        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/CustomerPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerPageController controller;
        controller = loader.getController();

        controller.setUser(user);
        controller.us();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1280, 750));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.showAndWait();

    }

    private void least() throws SQLException, ClassNotFoundException {


        DatabaseHandler databaseHandler6 = new DatabaseHandler();
        ResultSet resultSet = databaseHandler6.fillOptions();


        // resultSet.getString(1);
        int i = 1;
        while (resultSet.next() /*i<5*/) {
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: #5b977a;");
              borderPane.setPadding(new Insets(10, 10, 10, 10));

            HBox hhh = new HBox();
            hhh.setStyle("-fx-background-color: #fff6e3;");

            Label lab = new Label();
            lab.setStyle("-fx-font-size: 20px; -fx-text-fill:#022716; ");
            lab.setText(resultSet.getString(6));
            lab.setMaxWidth(800);
            lab.setWrapText(true);
            hhh.getChildren().add(lab);
hhh.setPadding(new Insets(5, 5, 5, 5));
     /*   Button but = new Button();
        but.setText("Кнопка" + i);

        but.setOnAction(event -> {

            System.out.println( but + "Clicked!");
        });*/

            Label lab1 = new Label();
            lab1.setText(resultSet.getString(7));
            lab1.setStyle("-fx-font-size: 18px;-fx-text-fill: #035644;  ");
            lab1.setMaxWidth(800);
            lab1.setWrapText(true);
            Label status = new Label();
            status.setText(resultSet.getString(4));
            status.setStyle("-fx-font-size: 18px;  -fx-text-fill: #c2dcd9;-fx-padding: 10px;");
            status.setMaxWidth(800);
            status.setWrapText(true);

            borderPane.setTop(hhh);
            borderPane.setLeft(lab1);
            borderPane.setRight(status);
//        if(user.getAction().isEmpty())System.out.println("PIZDA");
            //  if(user.getAction().equals("freelancer"))   borderPane.setRight(but);

//hui.getChildren().add(but);

            listt.getItems().add(borderPane/*resultSet.getString(1)*/);
            i++;
        }


    }

    private void workFunc() throws SQLException {
        if (workFlag == false) {
            listt1.getStyleClass().add("Styles.css");
            DatabaseHandler databaseHandler6 = new DatabaseHandler();
            ResultSet resultSet = null;
            try {
                resultSet = databaseHandler6.fillOptions();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            JFXButton sortBut= new JFXButton();
            sortBut.setPrefSize(300,38);
            sortBut.setText("Отсортировать по убыванию цены");
            sortBut.setStyle("-fx-background-color:#daa384;-fx-font-size: 16px; -fx-text-fill:#fff6e3;");
            BorderPane borderPane1 = new BorderPane();
            borderPane1.setStyle("-fx-background-color: #5b977a;");
            borderPane1.setPadding(new Insets(5, 5, 5, 5));
            borderPane1.setRight(sortBut);
            sortBut.setOnAction(event1 -> {
                listt1.getItems().clear();
DatabaseHandler databaseHandler111 = new DatabaseHandler();
                try {
                  ResultSet resultSet1 =   databaseHandler111.sort();
                    int i = 1;
                    int flag = 1;
                    if (user.getAction().equals("customer")) flag = 0;
                    listt1.getItems().add(borderPane1);
                    while (true /*i<5*/) {
                        try {
                            if (!resultSet1.next()) break;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        BorderPane borderPane = new BorderPane();
                        borderPane.setStyle("-fx-background-color: #5b977a;");
                        borderPane.setPadding(new Insets(10, 10, 10, 10));

                        HBox hhh = new HBox();

                        hhh.setStyle("-fx-background-color: #fff6e3;");
                        hhh.setPadding(new Insets(10, 10, 10, 10));
                        //   hui.setPrefSize(200,100);
                        Label lab = new Label();
                        lab.setStyle("-fx-font-size: 20px; -fx-text-fill:#022716; ");
                        try {
                            lab.setText(resultSet1.getString(6));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        lab.setMaxWidth(800);
                        lab.setWrapText(true);
                        hhh.getChildren().add(lab);

                        final int orderID;



                          JFXButton but = new JFXButton();
                          but.setPrefSize(130, 38);
                          but.setText("Взять задание");

                          but.setStyle("-fx-background-color:#c2dcd9;-fx-font-size: 16px;");

                          try {
                              orderID = resultSet1.getInt(1);

                              but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                                  @Override
                                  public void handle(MouseEvent event) {
                                      //     Stage stage = (Stage) but.getScene().getWindow();

                                      DatabaseHandler databaseHandler9 = new DatabaseHandler();
                                      try {
                                          databaseHandler9.request(orderID, user.getId());
                                          databaseHandler9.updateOrders(orderID);
                                      } catch (SQLException e) {
                                          e.printStackTrace();
                                      } catch (ClassNotFoundException e) {
                                          e.printStackTrace();
                                      }

                                      but.setVisible(false);
                                      event.consume();
                                  }
                              });

                          } catch (SQLException e) {
                              e.printStackTrace();
                          }

                        Label lab1 = new Label();
                        try {
                            lab1.setText(resultSet1.getString(7));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        lab1.setStyle("-fx-font-size: 18px;-fx-text-fill: #035644;  ");
                        lab1.setMaxWidth(800);
                        lab1.setWrapText(true);
                        Label status = new Label();
                        try {
                            status.setText(resultSet1.getString(4));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        status.setStyle("-fx-font-size: 18px; -fx-text-fill: #c2dcd9;-fx-padding: 10px;");
                        status.setMaxWidth(800);
                        status.setWrapText(true);
                        //  hui.getChildren().add(status);
                        borderPane.setTop(hhh);
                        borderPane.setLeft(lab1);
                        VBox vBox = new VBox();

                        vBox.getChildren().add(status);
                        Label price = new Label();
                        try {
                            price.setText(Integer.toString(resultSet1.getInt(5)) + " Рублей");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        price.setStyle("-fx-font-size: 18px; -fx-text-fill: #c2dcd9;-fx-padding: 10px;");
                        vBox.getChildren().add(price);
                        if (flag == 1) borderPane.setBottom(but); //vBox.getChildren().add(but);
                        borderPane.setRight(vBox);

                        listt1.getItems().add(borderPane/*resultSet.getString(1)*/);
                        i++;
                    }

                    setWorkFlag(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            });



            listt1.getItems().add(borderPane1);
            // resultSet.getString(1);
            int i = 1;
            int flag = 1;
            if (user.getAction().equals("customer")) flag = 0;
            while (true /*i<5*/) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                BorderPane borderPane = new BorderPane();
                borderPane.setStyle("-fx-background-color: #5b977a;");
                borderPane.setPadding(new Insets(10, 10, 10, 10));

                HBox hhh = new HBox();

                hhh.setStyle("-fx-background-color: #fff6e3;");
                hhh.setPadding(new Insets(10, 10, 10, 10));
                //   hui.setPrefSize(200,100);
                Label lab = new Label();
                lab.setStyle("-fx-font-size: 20px; -fx-text-fill:#022716; ");
                try {
                    lab.setText(resultSet.getString(6));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                lab.setMaxWidth(800);
                lab.setWrapText(true);
                hhh.getChildren().add(lab);

                final int orderID;

                if (resultSet.getString(4).equals("OPEN")) {
                    JFXButton but = new JFXButton();
                    but.setPrefSize(130, 38);
                    but.setText("Взять задание");

                    but.setStyle("-fx-background-color:#c2dcd9;-fx-font-size: 16px;");

                    try {
                        orderID = resultSet.getInt(1);

                        but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {
                                //     Stage stage = (Stage) but.getScene().getWindow();

                                DatabaseHandler databaseHandler9 = new DatabaseHandler();
                                try {
                                    databaseHandler9.request(orderID, user.getId());
                                    databaseHandler9.updateOrders(orderID);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }

                                but.setVisible(false);
                                event.consume();
                            }
                        });

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (flag == 1) borderPane.setBottom(but);
                }
                Label lab1 = new Label();
                try {
                    lab1.setText(resultSet.getString(7));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                lab1.setStyle("-fx-font-size: 18px;-fx-text-fill: #035644;  ");
                lab1.setMaxWidth(800);
                lab1.setWrapText(true);
                Label status = new Label();
                try {
                    status.setText(resultSet.getString(4));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                status.setStyle("-fx-font-size: 18px; -fx-text-fill: #c2dcd9;-fx-padding: 10px;");
                status.setMaxWidth(800);
                status.setWrapText(true);
                //  hui.getChildren().add(status);
                borderPane.setTop(hhh);
                borderPane.setLeft(lab1);
                VBox vBox = new VBox();

                vBox.getChildren().add(status);
                Label price = new Label();
                try {
                    price.setText(Integer.toString(resultSet.getInt(5)) + " Рублей");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                price.setStyle("-fx-font-size: 18px; -fx-text-fill: #c2dcd9;-fx-padding: 10px;");
                vBox.getChildren().add(price);
                //vBox.getChildren().add(but);
                borderPane.setRight(vBox);

                listt1.getItems().add(borderPane/*resultSet.getString(1)*/);
                i++;
            }

            setWorkFlag(true);
        }

    }
}
