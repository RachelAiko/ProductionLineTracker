

public class GenericProduct extends Product {

  public GenericProduct(String name, ItemType type, String manufacturer) {
    super(name, type, manufacturer);

  }

  @Override
  public ItemType getItemType() {
    return null;
  }

  @Override
  public void setItemType(ItemType itemType) {

  }

}
