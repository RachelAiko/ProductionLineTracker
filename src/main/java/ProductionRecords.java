/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: ProductionRecords.java
 *
 * Defines the ProductionRecords class.
 */

import java.util.Date;


/**
 * Represents the production records.
 * <p>
 * Creates a production record.
 * </p>
 *
 * @author Rachel Matthews
 */
public class ProductionRecords {

  // Unique production number of the record.
  private int productionNumber;

  // Id number of the product being produced.
  private int productionId;

  // The serial number of the product.
  private String serialNumber;

  // The date of production.
  private Date dateProduced;


  /**
   * Creates a record for a new product.
   *
   * @param productionId The production number of the product.
   */
  public ProductionRecords(int productionId) {

    this.productionNumber = 0;
    this.productionId = productionId;
    this.serialNumber = getSerialNumber();
    this.dateProduced = new Date();

  }

  /**
   * Creates a production record for a produced product with count.
   *
   * @param product         The instance of the product.
   * @param productionCount The product count.
   */
  public ProductionRecords(Product product, int productionCount) {

    ItemType type = product.getType();
    String paddedNumOfProduct = String.format("%05d", productionCount);
    String manufacturer = product.getManufacturer().substring(0, 3);
    serialNumber = manufacturer + type.code + paddedNumOfProduct;
    dateProduced = new Date();

  }


  /**
   * Overloaded constructor used when creating ProductionRecord objects from database.
   *
   * @param productionNumber The production number.
   * @param productionId     The production Id number.
   * @param serialNumber     The serial number.
   */
  public ProductionRecords(int productionNumber, int productionId, String serialNumber,
      Date dateProduced) {

    this.productionNumber = productionNumber;
    this.productionId = productionId;
    this.serialNumber = serialNumber;

  }

  /**
   * Generates a serial number based on the manufacturer, type, and production count.
   *
   * @param manufacturer    The production number.
   * @param type            The production Id number.
   * @param productionCount The production count.
   */
  public static String generateSerialNumber(String manufacturer, ItemType type,
      int productionCount) {
    return manufacturer.substring(0, 3).toUpperCase() + type.getCode() + String
        .format("%05d", productionCount);
  }

  /**
   * Gets the recorded production number.
   *
   * @return the production number
   */
  public int getProductionNumber() {

    return productionNumber;
  }

  /**
   * Sets the recorded production number.
   */
  public void setProductionNumber(int productionNumber) {

    this.productionNumber = productionNumber;

  }

  /**
   * Gets the recorded product identification number.
   *
   * @return the production Id number.
   */
  public int getProductionId() {

    return productionId;

  }

  /**
   * Sets the recorded product identification number.
   */
  public void setProductionId(int productionId) {

    this.productionId = productionId;

  }

  /**
   * Gets the recorded product serial number.
   *
   * @return the product serial number.
   */
  public String getSerialNumber() {

    return serialNumber;

  }

  /**
   * Sets the recorded product serial number.
   */
  public void setSerialNumber(String serialNumber) {

    this.serialNumber = serialNumber;

  }

  /**
   * Gets the recorded date of production.
   *
   * @return the date the product was produced.
   */
  public Date getDateProduced() {

    return new Date(dateProduced.getTime());

  }

  // sets the recorded date of production
  public void setDateProduced(Date dateProduced) {

    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Generates a serial number using the given properties.
   *
   * @param manufacturer    The manufacturers name.
   * @param type            The type of product.
   * @param productionCount The production count.
   * @return A serial number in the form of a string.
   */
  public String genSerialNumber(String manufacturer, ItemType type, int productionCount) {

    return manufacturer.substring(0, 3).toUpperCase()
        + type.getCode()
        + String.format("%05d", productionCount);
  }

  /**
   * Generates a serial number using the given properties.
   *
   * @return A string formatted that lists the production number, id, serial number, and date.
   */
  public String toString() {

    return String.format("Production Num: %d Product Id: %s Serial Num: %s Date: %s",
        productionNumber, productionId, serialNumber, dateProduced
    );
  }
}