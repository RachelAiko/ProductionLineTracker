/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020 
 * PROJ: ProductionLineTracker 
 * FILE: Screen.java
 * 
 * Defines the Screen class.
 * 
 */

public class Screen implements ScreenSpec {

  private final String resolution;

  private final int refreshRate;

  private final int responseTime;

  /**
   * Takes in screen specifications and creates a screen.
   *
   * @param resolution   The resolution of the screen.
   * @param refreshRate  The refresh rate of the screen.
   * @param responseTime The response time of the screen.
   */
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

  /**
   * Gets the Screen information.
   *
   * @return A string containing the screens information.
   */
  @Override
  public String toString() {

    return String.format(
        "%nResolution: %s%nRefresh rate: %s%nResponse time: %s",
        getResolution(), getRefreshRate(), getResponseTime()
    );
  }
}
