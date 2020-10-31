public enum ItemType {

  AUDIO("Audio","AU"), VISUAL("Visual","VI"), AUDIO_MOBILE("AudioMobile","AM"), VISUAL_MOBILE("VisualMobile","VM");

  public final String type;
  public final String code;

  ItemType(String code, String type) {

    this.code = code;
    this.type = type;
  }

  public static String getType(String type) {
    return type;
  }

  public static String getCode(String code) {
    return code;
  }

  public String getCode() {
    return code;
  }

  public String getType() {
    return type;
  }

}
