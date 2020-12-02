/**
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: GenericProduct.java
 *
 * Defines the GenericProduct class.
 */

public class GenericProduct extends Product {

  public GenericProduct(String name, ItemType type, String manufacturer) {
    super(name, type, manufacturer);

  }

  public GenericProduct(int lastId, String name, String manufacturer, ItemType item) {
    super(lastId, name, manufacturer, item);
  }

  /* public GenericProduct(int Id, String name, ItemType type, String manufacturer) {
     super(Id,name, type, manufacturer);

   }
 */
  @Override
  public ItemType getItemType() {
    return type;
  }

  @Override
  public void setItemType(ItemType itemType) {

  }

}
