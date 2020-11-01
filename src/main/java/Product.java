public abstract class Product implements Item {

  private int Id;
  private ItemType type;
  private String manufacturer;
  private String name;


  Product(String name, String manufacturer, ItemType type) {
    this.Id = 0;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public Product(String name, ItemType itemType, String manufacturer) {
  }


  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type.getCode();
  }

  public int getId() {
    return Id;
  }

  public void setId(int Id) {
    Id = Id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }
}


