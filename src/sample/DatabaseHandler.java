package sample;

import com.mysql.cj.jdbc.Driver;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;

/*private static final String URL = "jdbc:mysql://localhost:3306/dbname";
    private static final String USERNAME = "bla-bla";
    private static final String PASSWORD = "bla-bla";

public static void main(String[] args) {
Connection connection;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        }catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }
}*/
public class DatabaseHandler extends Configs {
    Connection connection;

    public Connection getConnection()
            throws ClassNotFoundException, SQLException {


        String connectionString = "jdbc:mysql://localhost:3306/freelanceproject" + "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, User, Pass);


        //    System.out.println("Не удалось загрузить класс драйвера");

        return connection;
    }

    public void newOrder(String title, String text, int price) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.ORDER_TITLE + "," + Const.ORDER_DESCRIPTION + "," + Const.ORDER_PRICE + ")" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, text);
        preparedStatement.setInt(3, price);

        preparedStatement.executeUpdate();

    }

    public void signUpUser(String login, String password, String action) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_LOGIN + "," + Const.USER_PASSWORD + "," + Const.USER_ACTION + ")" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, action);

        preparedStatement.executeUpdate();
    }

    public void imageToDatabase(File file, User user) throws SQLException, ClassNotFoundException {
        FileInputStream fileInputStream;

        try {

            String insert = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_PHOTO + " =?" + " WHERE " + Const.USER_ID + " = " + user.getId();
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            fileInputStream = new FileInputStream(file);
            preparedStatement.setBinaryStream(1, (InputStream) fileInputStream, (int) file.length());

            preparedStatement.executeUpdate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
public void updateOrders(int orderID) throws SQLException, ClassNotFoundException {
    String insert = "UPDATE ordersbase SET Status = ?  WHERE OrderID = ?" ;
    PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
    preparedStatement.setString(1,"PERFORMED");
    preparedStatement.setInt(2,orderID);
    preparedStatement.executeUpdate();
}
    public void updateUser(int UserID,String text) throws SQLException, ClassNotFoundException {
        String insert = "UPDATE users SET description = ? WHERE ID = ?" ;
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
        preparedStatement.setString(1,text);
        preparedStatement.setInt(2,UserID);
        preparedStatement.executeUpdate();
    }


    public ResultSet myOrdersCustomer(int CustomerID) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + Const.ORDER_TABLE + " WHERE CustomerID = " + CustomerID;
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    public ResultSet myOrdersCustomer2(int ID) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + Const.CLOSED_ORDER_TABLE + " WHERE orderID = " + ID;
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    public ResultSet myOrdersCustomerUsers(int userID) throws SQLException, ClassNotFoundException {
        String select = "SELECT userImage FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + " = " + userID;
        PreparedStatement preparedStatement = getConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
public void deleteOrder(int orderID) throws SQLException, ClassNotFoundException {
        System.out.println("В запросе " + orderID );
        String delete = "DELETE FROM ordersbase WHERE (OrderID =" + orderID+")";
    PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
   // preparedStatement.setInt(1, orderID);
   preparedStatement.executeUpdate();

}

    public ResultSet fillOptions() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.ORDER_TABLE;
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

       return resultSet;

        //preparedStatement.close();
     //   resultSet.close();

    }


    public ResultSet getfreelancer() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE action = 'employee'";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;

        //preparedStatement.close();
        //   resultSet.close();

    }

    public Image imageFromDatabase(User user) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = null;
        String select = "SELECT userImage FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + " = " + user.getId();
        PreparedStatement preparedStatement = getConnection().prepareStatement(select);
        resultSet = preparedStatement.executeQuery();
        InputStream inputStream = null;
        while (resultSet.next()) {
            inputStream = resultSet.getBinaryStream("userImage");

        }
        OutputStream outputStream = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;

        while ((size = inputStream.read(content)) != -1) {
            outputStream.write(content, 0, size);
        }
        outputStream.close();
        inputStream.close();
        Image image = new Image("file:photo.jpg", 100, 150, true, true);
        return image;
    }
      public void request(int orderID, int employeeID) throws SQLException, ClassNotFoundException {
          String insert = "INSERT INTO " + Const.CLOSED_ORDER_TABLE + "( orderID,freelancerID )" + "VALUES(?,?)";
          PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

          preparedStatement.setInt(1, orderID);
          preparedStatement.setInt(2, employeeID);

          preparedStatement.executeUpdate();

      }


    public void setNewOrder(int customerID, int orderPrice, String orderTitle, String orderdescription) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.ORDER_TABLE + "(" +
                Const.ORDER_CUSTOMERID + "," + Const.ORDER_STATUS + "," + Const.ORDER_PRICE + "," + Const.ORDER_TITLE + "," + Const.ORDER_DESCRIPTION + ")" + "VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

        preparedStatement.setInt(1, customerID);
        preparedStatement.setString(2, "OPEN");
        preparedStatement.setInt(3, orderPrice);
        preparedStatement.setString(4, orderTitle);
        preparedStatement.setString(5, orderdescription);

        preparedStatement.executeUpdate();

    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());


            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet sort() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.ORDER_TABLE + " ORDER BY Price DESC";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

}
