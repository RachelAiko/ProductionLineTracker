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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Statement;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  public Tab tab1;
  public Tab tab2;
  public Tab tab3;
/*  @FXML
  private Label lblOutput;*/

  @FXML
  private TextField txtManufacturerName;

  @FXML
  private TextField txtProductName;

   @FXML
  private TextArea TxtProductionLog;

  /**
   * The 'products' table.
   */
  @FXML
  private TableView<GenericProduct> tblProducts;

  /**
   * The 'products' table 'id' column.
   */
  @FXML
  private TableColumn<?, Product> colProductId;

  /**
   * The 'products' table 'name' column.
   */
  @FXML
  private TableColumn<?, Product> colProductName;

  /**
   * The 'products' table 'type' column.
   */
  @FXML
  private TableColumn<?, Product> colProductType;

  /**
   * The 'products' table 'manufacturer' column.
   */
  @FXML
  private TableColumn<?, Product> colProductManufacturer;


  @FXML
  private ChoiceBox<ItemType> itemChoice;


  @FXML
  private ComboBox<String> chooseQuantity;

  ObservableList<GenericProduct> data = FXCollections.observableArrayList();

  @FXML
  private void initialize() {

    initializeDB();
   // Data.open();

    initializeItemChoice();

    initializeQuantityBox();

    setupProductLineTable();

    initializeProductionLog();

    //Data.loadProductionLog();
    productionLog.addAll();
    productLine.addAll();

    //productionLog.addAll(Data.loadProductionLog());

  }

  //Generates a serial number using the given properties.
  public static String genSerialNumber(String manufacturer, ItemType type, int productionCount) {

    return manufacturer.substring(0, 3).toUpperCase()
        + type.getCode()
        + String.format("%05d", productionCount);
  }

  // The list of production records loaded from the database.
  private final ObservableList<ProductionRecord> productLine =
      FXCollections.observableArrayList();


  //The list of production records loaded from the database.
  private final ObservableList<ProductionRecord> productionLog =
      FXCollections.observableArrayList();


  //Populates comboBox for quantity
  private void initializeQuantityBox() {
    for (int count = 1; count <= 10; count++) {
      chooseQuantity.getItems().add(String.valueOf(count));
      chooseQuantity.getSelectionModel().selectFirst();
      chooseQuantity.setEditable(true);
    }
  }

  //Populates choice box for Item Type
  private void initializeItemChoice() {
    itemChoice.getItems().clear();
    itemChoice.getItems().addAll(ItemType.values());
    itemChoice.getSelectionModel().selectFirst();
  }


  @FXML
   void addProduct(ActionEvent event) throws SQLException  {

    //Prints to terminal when add button is pushed
    System.out.println("Product Added");
    initializeDB();

    //Gets product name and manufacturer from GUI
    String name = txtProductName.getText();
    ItemType type = itemChoice.getValue();
    System.out.println(type);
    String manufacturer= txtManufacturerName.getText();

    GenericProduct newProduct = new GenericProduct(name, type, manufacturer);

    //setupProduceListview();
   data.add(newProduct);


   /* Product newProduct = addProduct(name, type, manufacturer);
    productLine.add(newProduct);*/

  }

  @FXML
  private void recordProduction(ActionEvent event) throws SQLException {

    //Prints to terminal when record production button is pushed
    System.out.println("Production Recorded");
    initializeProductionLog();

    productionLog.addAll();

  }


  //Initializes the 'products' table data.
  private void setupProductLineTable() {

    //ObservableList<GenericProduct> data = productLine();

    colProductId.setCellValueFactory(new PropertyValueFactory<>("Id"));
    colProductName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    colProductType.setCellValueFactory(new PropertyValueFactory<>("type"));
    colProductManufacturer.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));

    tblProducts.setItems(data);

  }

  private ObservableList<GenericProduct> productLine() {
    //return FXCollections.observableArrayList(productionLog);
    return FXCollections.observableArrayList();

  }

  //Initializes the 'production log' text area.
  private void initializeProductionLog() {

    TxtProductionLog.setText("");
    productionLog.addListener(
        (ListChangeListener<ProductionRecord>) c -> {
          while (c.next()) {
            appendTxtProdsLog(c.getAddedSubList());
          }
        }
    );
  }

  //Appends the given records to the 'prods log' text area.
  private void appendTxtProdsLog(List<? extends ProductionRecord> list) {

    TxtProductionLog.appendText(
        list.stream()
            .filter(Objects::nonNull)
            .map(ProductionRecord::toString)
            .collect(Collectors.joining("\n")) + "\n"
    );
  }



  //Opens database connection. Executes query Cleans up and closes connection
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

      //Gets product name and manufacturer from GUI
      String name = txtProductName.getText();
      ChoiceBox<ItemType> type = itemChoice;
      String manufacturer = txtManufacturerName.getText();


      //Hard codes a product into database table product
      //String insertSql = "INSERT INTO product(Name, Type, Manufacturer ) VALUES ( 'iPod', 'Audio', "
         // + "'Apple')";

      //JDBC PreparedStatement
      PreparedStatement ps = conn
          .prepareStatement("INSERT INTO product (name, type, manufacturer) VALUES (?, ?, ?);");

      // add the given properties to the database...
      ps.setString(1, name);
     // ps.setString(2, type.getCode());
      ps.setString(2,"audio");
      ps.setString(3, manufacturer);

      ps.executeUpdate();

      String sql = "SELECT id, name, type, manufacturer" + " FROM PRODUCT ";

      //stmt.executeUpdate(insertSql);


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

 /* // tests the functionality of user interface
  public static void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }*/
  }

