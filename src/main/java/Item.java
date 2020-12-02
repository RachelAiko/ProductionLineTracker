/**
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: Item.java
 *
 * Defines the Item interface.
 */

public interface Item {

  int getId();
  void setName(String name);
  String getName();
  void setManufacturer(String manufacturer);
  String getManufacturer();
  ItemType getItemType();
  void setItemType(ItemType itemType);
}
