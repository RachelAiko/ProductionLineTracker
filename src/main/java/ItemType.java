public enum ItemType {

  AUDIO("Audio","AU"), VISUAL("Visual","VI"), AUDIO_MOBILE("AudioMobile","AM"), VISUAL_MOBILE("VisualMobile","VM");

  public final String type;
  public final String code;

  ItemType(String code, String type) {

    this.code = code;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public String getCode() {
    return code;
  }
}
