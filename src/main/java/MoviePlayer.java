/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: MoviePlayer.java
 *
 * Defines the MoviePlayer class.
 */

/**
 * This class creates a movie player product.
 *
 * @author Rachel Matthews
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  /**
   * Takes in a products information and returns an instance of a movie player product.
   *
   * @param name         The name of the product.
   * @param manufacturer The name of the manufacturer of the product.
   * @param screen       The screen information of the product.
   * @param monitorType  The monitor type of the product.
   * @param lastId       The last Id number in the database.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType,
      int lastId) {

    super(lastId, name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Gets the Screen information.
   *
   * @return this screens information.
   */
  public Screen getScreen() {
    return screen;
  }

  /**
   * Gets the Monitor information.
   *
   * @return this monitor type information.
   */
  public MonitorType getMonitorType() {
    return monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing Movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping Movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous Movie");
  }

  @Override
  public void next() {
    System.out.println("Next Movie");
  }

  /**
   * Creates a string with the information of the product.
   *
   * @return This product's Screen and monitor type.
   */
  public String toString() {

    return super.toString() + String.format(
        "%nScreen: %s%nMonitorType: %s",
        getScreen(), getMonitorType()
    );
  }

  @Override
  public ItemType getItemType() {
    return null;
  }

  @Override
  public void setItemType(ItemType itemType) {

  }
}
