/**
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: Screen.java
 *
 * Defines the Screen interface for accessing screen specification.
 */

public interface ScreenSpec {

  String getResolution();

  int getRefreshRate();

  int getResponseTime();

}
