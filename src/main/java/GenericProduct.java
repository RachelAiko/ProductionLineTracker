

public class GenericProduct extends Product {

  public GenericProduct(  String name, ItemType itemType, String manufacturer) {
    super( name,itemType, manufacturer);

  }

  public GenericProduct(String name, String type, String manufacturer, ItemType itemType) {
    super(name, itemType, manufacturer);
  }
}
