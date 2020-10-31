public abstract class Product implements Item {

  private int Id;
  private ItemType type;
  private String manufacturer;
  private String name;



  Product( String name, String manufacturer, ItemType type) {
    this.Id = Id;
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
    name = name;
  }

  public ItemType getItemType() {
    return type;
  }

  public void setItemType(ItemType itemType) {
    type = itemType;
  }
}

class Widget extends Product {

  public Widget(String name, String manufacturer, ItemType itemType) {
    super(name, manufacturer, itemType); //uses a call to the parents class constructor
                                         //product is abstract
  }
}
