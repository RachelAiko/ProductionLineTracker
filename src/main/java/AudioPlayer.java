public class AudioPlayer extends Product implements MultimediaControl {

  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;

  AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;

  }

  public String getSupportedAudioFormats() {
    return supportedAudioFormats;
  }

  public void setSupportedAudioFormats() {

  }

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

  @Override
  public String toString() {

    return super.toString() + String.format( "%nSupported Audio Formats: %s%nSupported Playlist Formats: %s",
        getSupportedAudioFormats(), getSupportedPlaylistFormats());

  }
}

