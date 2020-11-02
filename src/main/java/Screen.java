public class Screen implements ScreenSpec {

  private final String resolution;

  private final int refreshRate;

  private final int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {

    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }


  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  @Override
  public String toString() {

    return String.format(
        "%nResolution: %s%nRefresh rate: %s%nResponse time: %s",
        getResolution(), getRefreshRate(), getResponseTime()
    );
  }
}
