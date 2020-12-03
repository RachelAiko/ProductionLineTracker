/*
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: ItemType.java
 *
 * Defines the ItemType enum.
 */

/**
 * The enum that consists of the different product item types.
 */
public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");


  public final String code;

  ItemType(String code) {

    this.code = code;

  }

  /**
   * Gets this products code information.
   *
   * @return this products code.
   */
  public String getCode() {
    return code;
  }


}
