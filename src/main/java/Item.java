public interface Item {

  int getId();
  void setName(String name);
  String getName();
  void setManufacturer(String manufacturer);
  String getManufacturer();
  ItemType getItemType();
  void setItemType(ItemType itemType);
}
