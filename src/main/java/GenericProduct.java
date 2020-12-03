/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: GenericProduct.java
 *
 * Defines the GenericProduct class.
 */

/**
 * This class creates an object that is a generic representation of a product.
 *
 * @author Rachel Matthews
 */
public class GenericProduct extends Product {

  public GenericProduct(String name, ItemType type, String manufacturer) {
    super(name, type, manufacturer);

  }

  public GenericProduct(int lastId, String name, String manufacturer, String item) {
    super(lastId, name, manufacturer, ItemType.valueOf(item));
  }

  @Override
  public ItemType getItemType() {
    return type;
  }

  @Override
  public void setItemType(ItemType itemType) {

  }

}
