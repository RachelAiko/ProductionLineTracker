import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {


    @FXML
    private Label lblOutput;

    @FXML
    private void sayHello() {
        lblOutput.setText("Hello!");
    }

    @FXML
    private void addProduct() {
        System.out.println("Product Added");
    }

    @FXML
    private void recordProduction() {
        System.out.println("Production Recorded");
    }

    @FXML
    private void itemType() {
    }

    @FXML
    private void chooseQuantity() {
        List<String> chooseQuantity = new ArrayList<String>();
        chooseQuantity.add("1");
        chooseQuantity.add("2");
        chooseQuantity.add("3");
    }

    @FXML
    private void initialize() {
        initializeDB();
    }
    public void initializeDB() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/PLdb";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT * FROM PRODUCT";


            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/PLdb";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

       /*try {
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        //conn = DriverManager.getConnection(DB_URL, USER, PASS);
        conn = DriverManager.getConnection(DB_URL);


        //STEP 3: Execute a query
        stmt = conn.createStatement();


        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

        // STEP 4: Clean-up environment
        stmt.close();
        conn.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();

    } catch (
    SQLException e) {
        e.printStackTrace();
    }*/

}