package oit.is.z2883.kaizi.janken.model;

public class MatchInfo {
  long id;
  long user1;
  long user2;
  String user1Hand;
  boolean isActive;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUser1() {
    return user1;
  }

  public void setUser1(long user1) {
    this.user1 = user1;
  }

  public long getUser2() {
    return user2;
  }

  public void setUser2(long user2) {
    this.user2 = user2;
  }

  public String getUser1Hand() {
    return user1Hand;
  }

  public void setUser1Hand(String user1Hand) {
    this.user1Hand = user1Hand;
  }

  public boolean isIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }
}
