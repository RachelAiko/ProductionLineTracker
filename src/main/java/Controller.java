import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class Controller {

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
  private ComboBox<String> chooseQuantity;

  @FXML
  private void initialize() {
    initializeDB();
    for (int count = 1; count <= 10; count++) {
      chooseQuantity.getItems().add(String.valueOf(count));
    }

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
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");

      //STEP 3: Execute a query
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();

      // Gets product name and manufacturer from GUI
      String name = txtProductName.getText();
      String manufacturer = txtManufacturerName.getText();

      String insertSQL = "INSERT INTO product(NAME, TYPE, MANUFACTURER ) VALUES ( 'iPod', 'Audio', "
                          +"'Apple')";

      String sql = "SELECT id, name, type, manufacturer" + " FROM PRODUCT ";

      stmt.executeUpdate(insertSQL);

     /* ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      String id = rs.getString(1);
      String name = rs.getString(2);
      String type = rs.getString(3);
      String manufacturer = rs.getString(4);
      System.out.println(id + " " + name + " " + type + " " + manufacturer + " ");*/

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}