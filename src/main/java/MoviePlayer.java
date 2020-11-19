public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType,
      int lastId) {

    super(lastId, name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public Screen getScreen() {
    return screen;
  }

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
