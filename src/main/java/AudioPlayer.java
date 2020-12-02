/**
 * AUTH: Rachel Matthews
 * DATE: Sat, Sep 19th, 2020
 * PROJ: ProductionLineTracker
 * FILE: AudioPlayer.java
 *
 * Defines the AudioPlayer class.
 */

/**
 * A representation of an Audio player product with multimedia controls.
 *
 * @author Rachel Matthews
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * The audio formats supported by this audio player.
   */
  private final String supportedAudioFormats;

  /**
   * The playlist formats supported by this audio player.
   */
  private final String supportedPlaylistFormats;


  /**
   * Creates an audio player product.
   *
   * <p>
   * A default id of 0 is automatically assigned.
   * </p>
   *
   * @param name                     the display name
   * @param manufacturer             the manufacturer name
   * @param supportedAudioFormats    supported audio formats
   * @param supportedPlaylistFormats supported playlist formats
   */
  AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats, int lastId) {

    super(lastId, name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;

  }

  /**
   * Gets the audio formats supported by this audio player.
   *
   * @return the supported audio formats
   */
  public String getSupportedAudioFormats() {

    return supportedAudioFormats;
  }

  public void setSupportedAudioFormats() {

  }

  /**
   * Gets the playlist formats supported by this audio player.
   *
   * @return the supported playlist formats
   */
  public String getSupportedPlaylistFormats() {

    return supportedPlaylistFormats;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Creates formatted string of products information.
   */
  @Override
  public String toString() {

    return super.toString() + String.format( "%nSupported Audio Formats: %s%nSupported Playlist Formats: %s",
        getSupportedAudioFormats(), getSupportedPlaylistFormats());

  }

  /**
   * Gets the items type.
   *
   * @return ItemType
   */
  @Override
  public ItemType getItemType() {
    return getType();
  }

  /**
   * Sets the items type.
   */
  @Override
  public void setItemType(ItemType itemType) {

  }

}

