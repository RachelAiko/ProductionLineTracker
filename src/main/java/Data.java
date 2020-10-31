import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Data {

  //The database connection.
  private static Connection conn;


  //The name of the database properties file.
  private static final String PLdb_PROPERTIES = "db.properties";


  //gets the properties defined in the properties file.
  public static Properties getProperties() {

    Properties props = new Properties();

    try (InputStream is = Main.class.getClassLoader()
        .getResourceAsStream(PLdb_PROPERTIES)) {

      if (is == null) {
        throw new FileNotFoundException(String.format(
            "The resource '%s' was not found.", PLdb_PROPERTIES
        ));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }


  //Opens a connection to the database.
  public static void open() {

    if (conn != null) {
      close();
    }
    try {
      Properties props = getProperties();

      String dbUrl = (String) Objects.requireNonNull(props.remove("url"));
      String jdbcDriver = (String) Objects.requireNonNull(props.remove("driver"));

      Class.forName(jdbcDriver);
      conn = DriverManager.getConnection(dbUrl, props);

    } catch (SQLException | ClassNotFoundException
        | ClassCastException | NullPointerException ex) {
    }
  }

   //Closes the database connection.
  public static void close() {

    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ex) {

      }
      conn = null;
    }
  }


  //Tests the database connection.
  private static boolean isOpen() {

    if (conn != null) {
      try {
        if (conn.isValid(1)) {
          return true;
        }
      } catch (SQLException ex) {
        return false;
      }
    }
    return false;
  }


  private static void requireConnection() throws IllegalStateException {

    if (!isOpen()) {
      throw new IllegalStateException(
          "Attempted a database operation without a database connection.");
    }
  }


  //Adds a new product to the database and product line.
  public static GenericProduct addProduct(String name, ItemType type, String manufacturer)
      throws SQLException, IllegalArgumentException, IllegalStateException {

    requireConnection();

    try (PreparedStatement stmt = conn.prepareStatement(
        "INSERT INTO product (name, type, manufacturer) VALUES (?, ?, ?);")) {

      // validate the given properties...
      if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("A product name was not given.");
      }
      if (type == null) {
        throw new IllegalArgumentException("A product type was not selected.");
      }
      if (manufacturer == null || manufacturer.length() < 3) {
        throw new IllegalArgumentException("The Manufacturer name must be at least three chars.");
      }

      // add the given properties to the database...
      stmt.setString(1, name);
      stmt.setString(2, type.getCode());
      stmt.setString(3, manufacturer);
      stmt.execute();

      // create a new product to be returned...
      return new GenericProduct( name, type, manufacturer);
    }
  }


   //Records the production of a product to the database.
  public static List<ProductionRecord> addToProductionDB(Product prod, Integer quantity, Timestamp time
      ) throws SQLException, IllegalArgumentException, IllegalStateException {

    requireConnection();

    try (PreparedStatement stmt = conn.prepareStatement(
        "INSERT INTO productionRecord (productionId, serialNumber, date) VALUES (?, ?, ?, ?);"
    )) {

      // validate the given properties...
      if (prod == null) {
        throw new IllegalArgumentException("No product was selected.");
      }

      if (quantity == null || quantity < 1) {
        throw new IllegalArgumentException("Invalid quantity chosen.");
      }

      List<ProductionRecord> productionRun = new ArrayList<>();
      int productionCount = getProductionCount(prod.getId()) + 1;

      // iterate over the range of the given quantity...
      for (int i = 0; i < quantity; i++) {

        String serialNumber = genSerialNumber(
            prod.getManufacturer(), prod.getItemType(), productionCount++);

        // add the given properties to a new row on the ProductionRecords table...
        stmt.setInt(1, prod.getId());

        stmt.setString(3, serialNumber);
        stmt.setTimestamp(4, time);
        stmt.execute();

        // create a new record to be returned...
        productionRun.add(new ProductionRecord(
            getMaxProductionNumber(), prod.getId(), serialNumber,time));
      }
      return productionRun;
    }
  }



  //Gets a product from the database with the specified id.
  public static GenericProduct getProducts(int Id) throws IllegalStateException {

    requireConnection();

    try (PreparedStatement stmt = conn.prepareStatement(
        "SELECT * FROM product WHERE id = ?"
    )) {
      stmt.setInt(1, Id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {

          // get properties from the result set...
          String name = rs.getString("name");
          ItemType type = ItemType.valueOf(ItemType.getType(rs.getString("type")));
          String manufacturer = rs.getString("manufacturer");

          // return a new product of the appropriate class...
          return new GenericProduct(name, type, manufacturer);
        }
      } catch (IllegalArgumentException ex) {
        return null;
      }
    } catch (SQLException ex) {
      //Main.showError(ex);
    }
    return null;
  }



   //Gets a list of all products in the database.
  public static List<Product> loadProductList(ItemType itemType) throws IllegalStateException {

    requireConnection();

    List<Product> products = new ArrayList<>();

    try (
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM product");

        ResultSet rs = stmt.executeQuery()
    ) {
      while (rs.next()) {

        // get properties from the result set...
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String type;
        try {
          type = ItemType.getCode(rs.getString("type"));

        } catch (IllegalArgumentException ex) {
          continue;
        }
        String manufacturer = rs.getString("manufacturer");

        // create a new product of the appropriate class to be returned...
        products.add(new GenericProduct(name, type, manufacturer, itemType));
      }
    } catch (SQLException ex) {

    }
    return products;
  }


   //Gets a list of all production records in the database.
  public static List<ProductionRecord> loadProductionLog() throws IllegalStateException {

    requireConnection();

    List<ProductionRecord> records = new ArrayList<>();

    try (
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM productionRecord");

        ResultSet rs = stmt.executeQuery()
    ) {
      while (rs.next()) {

        // get properties from the result set...
        int productionNumber = rs.getInt("productionNumber");
        int productionId = rs.getInt("productionId");
        String serialNumber = rs.getString("serialnumber");
        Timestamp date = rs.getTimestamp("date");

        // create a new production record to be returned...
        records.add(new ProductionRecord(productionNumber, productionId, serialNumber, date));
      }

    } catch (SQLException ex) {

    }
    return records;
  }


   //Gets the maximum product id present in the database's PRODUCT table.
  private static int getMaxProductionId() throws IllegalStateException {

    requireConnection();

    try (
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT MAX(Id) AS id FROM product");

        ResultSet rs = stmt.executeQuery()
    ) {
      if (rs.next()) {
        return rs.getInt("Id");
      }
    } catch (SQLException ex) {

    }
    return 0;
  }


   //Gets the maximum production number present in the database's ProductionRecord table.
  private static int getMaxProductionNumber() throws IllegalStateException {

    requireConnection();

    try (
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT MAX(productionNumber) AS productionNumber FROM productionRecord");

        ResultSet rs = stmt.executeQuery()
    ) {
      if (rs.next()) {
        return rs.getInt("productionNumber");
      }
    } catch (SQLException ex) {

    }
    return 0;
  }

   //Gets the production count for a specified product.
  public static int getProductionCount(int productionId) throws IllegalStateException {

    requireConnection();

    try (PreparedStatement stmt = conn.prepareStatement(
        "SELECT COUNT(*) AS productionCount FROM produtionRecord WHERE (produtionId = ?)"
    )) {

      stmt.setInt(1, productionId);
      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
          return rs.getInt("productionCount");
        }
      }
    } catch (SQLException ex) {

    }
    return 0;
  }


   //Generates a serial number using the given properties.
  public static String genSerialNumber(String manufacturer, ItemType type, int productionCount) {

    return manufacturer.substring(0, 3).toUpperCase()
        + type.getCode()
        + String.format("%05d", productionCount);
  }

}
