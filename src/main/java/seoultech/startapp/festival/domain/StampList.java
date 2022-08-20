package seoultech.startapp.festival.domain;

public enum StampList {
  EXHIBITION("exhibition"),
  GROUND("ground"),
  FLEAMARKET("fleamarket"),
  BUNGEOBANG("bungeobang"),
  SANGSANG("sangsang");

  private final String stamp;

  StampList(String stamp) {
    this.stamp = stamp;
  }


  public void validation(String target){

  }
}
