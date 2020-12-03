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
 *
 * Creates a production record.
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
   * Creates a record for a new product
   *
   * @param productionId The production number of the product.
   */
  public ProductionRecords(int productionId) {

    this.productionNumber = 0;
    this.productionId = productionId;
    this.serialNumber = getSerialNumber();
    this.dateProduced = new Date();

  }

  // creates a record for a produced product with count
  public ProductionRecords(Product product, int productionCount) {

    ItemType type = product.getType();
    String paddedNumOfProduct = String.format("%05d", productionCount);
    String manufacturer = product.getManufacturer().substring(0, 3);
    serialNumber = manufacturer + type.code + paddedNumOfProduct;
    dateProduced = new Date();
    /*this.productionNumber = 0;
    this.productionId = product.getId();
    this.serialNumber = getSerialNumber();
    this.dateProduced = getDateProduced();*/

  }


  // overloaded constructor used when creating ProductionRecord objects from database
  public ProductionRecords(int productionNumber, int productionId, String serialNumber,
      Date dateProduced) {

    this.productionNumber = productionNumber;
    this.productionId = productionId;
    this.serialNumber = serialNumber;
    //this.dateProduced = new Date(dateProduced.getTime());
    // this.dateProduced = getDateProduced();

  }

  public static String generateSerialNumber(String manufacturer, ItemType type,
      int productionCount) {
    return manufacturer.substring(0, 3).toUpperCase() + type.getCode() + String
        .format("%05d", productionCount);
  }

  // gets the recorded production number
  public int getProductionNumber() {

    return productionNumber;
  }

  // sets the recorded production number
  public void setProductionNumber(int productionNumber) {

    this.productionNumber = productionNumber;

  }

  // gets the recorded product identification number
  public int getProductionId() {

    return productionId;

  }

  // sets the recorded product identification number
  public void setProductionId(int productionId) {

    this.productionId = productionId;

  }

  // gets the recorded product serial number
  public String getSerialNumber() {

    return serialNumber;

  }

  // sets the recorded product serial number
  public void setSerialNumber(String serialNumber) {

    this.serialNumber = serialNumber;

  }

  // gets the recorded date of production
  public Date getDateProduced() {

    return new Date(dateProduced.getTime());

  }

  // sets the recorded date of production
  public void setDateProduced(Date dateProduced) {

    this.dateProduced = new Date(dateProduced.getTime());
  }

  //Generates a serial number using the given properties.
  public String genSerialNumber(String manufacturer, ItemType type, int productionCount) {

    return manufacturer.substring(0, 3).toUpperCase()
        + type.getCode()
        + String.format("%05d", productionCount);
  }

  //@Override
  public String toString() {

    return String.format("Production Num: %d Product Id: %s Serial Num: %s Date: %s",
        productionNumber, productionId, serialNumber, dateProduced
    );
  }
}