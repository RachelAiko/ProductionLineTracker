/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: Controller.java
 *
 * Defines the Controller class.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  //Have not implemented preparedStatement yet.
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import org.h2.command.Prepared;   //Have not implemented preparedStatement yet.

public class Controller {

  public Tab tab1;
  public Tab tab2;
  public Tab tab3;
  @FXML
  private Label lblOutput;

  @FXML
  private TextField txtManufacturerName;

  @FXML
  private TextField txtProductName;

  @FXML
  private ChoiceBox<String> itemType;

  @FXML
  private void addProduct() {

    //Prints to terminal when add button is pushed
    System.out.println("Product Added");
  }

  @FXML
  private void recordProduction() {

    //Prints to terminal when record production button is pushed
    System.out.println("Production Recorded");
  }

  @FXML
  private void itemType() {
    System.out.println("Chose item type");
  }

  @FXML
  private ComboBox<String> chooseQuantity;

  @FXML
  private void initialize() {
    initializeDB();

    //Populates comboBox for quantity
    for (int count = 1; count <= 10; count++) {
      chooseQuantity.getItems().add(String.valueOf(count));
      chooseQuantity.getSelectionModel().selectFirst();
      chooseQuantity.setEditable(true);
    }

  }

  /**
   * Opens database connection. Executes query Cleans up and closes connection
   */
  public void initializeDB() {
    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_url = "jdbc:h2:./resources/PLdb";

    //  Database credentials
    final String User = "";                 //Intentionally left blank
    final String Pass = "";                 //Intentionally left blank
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(Db_url, User, Pass);
      System.out.println("Connected database successfully...");

      //STEP 3: Execute a query
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();

      //Gets product name and manufacturer from GUI (STILL WORKING ON IT)
      String name = txtProductName.getText();
      String manufacturer = txtManufacturerName.getText();

      //Hard codes a product into database table product
      String insertSql = "INSERT INTO product(Name, Type, Manufacturer ) VALUES ( 'iPod', 'Audio', "
          + "'Apple')";

      /*JDBC PreparedStatement  (YET TO BE IMPLEMENTED)
      PreparedStatement ps = conn
          .prepareStatement("INSERT INTO product(name,type,manufacturer)" + "VALUES (?, ?, ?)");

      ps.setString(1, name);
      ps.setString(2, "audio");
      ps.setString(3, manufacturer);

      ps.executeUpdate(); */

      String sql = "SELECT id, name, type, manufacturer" + " FROM PRODUCT ";

      stmt.executeUpdate(insertSql);

      //Prints the contents of the product table to terminal
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        String id = rs.getString(1);
        String productName = rs.getString(2);
        String productType = rs.getString(3);
        String productManufacturer = rs.getString(4);
        System.out
            .println(id + " " + productName + " " + productType + " " + productManufacturer + " ");
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
      rs.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}