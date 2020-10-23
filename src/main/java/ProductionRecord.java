import java.util.Date;

public class ProductionRecord {

  // unique production number of the record
  private int productionNumber;

  // id number of the product being produced
  private int productionId;

  // the serial number of the product
  private String serialNumber;

  // the date of production
  private Date dateProduced;

  // creates a record for a new product
  public ProductionRecord(int productionId) {

    this.productionNumber = 0;
    this.productionId = productionId;
    this.serialNumber = "0";
    this.dateProduced = new Date();

  }

  // overloaded constructor used when creating ProductionRecord objects from database
  public ProductionRecord(int productionNumber, int productionId, String serialNumber, Date dateProduced) {

    this.productionNumber = productionNumber;
    this.productionId = productionId;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());

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
  public Date getDateProduced () {

    return new Date(dateProduced.getTime());

  }

  // sets the recorded date of production
  public void setDateProduced(Date dateProduced) {

    this.dateProduced = new Date(dateProduced.getTime());
  }

  //@Overide
  public String toString() {

    return String.format("Production Num: %d Product Id: %s Serial Num: %s Date: %s",
        productionNumber, productionId, serialNumber, dateProduced
    );
  }

}
