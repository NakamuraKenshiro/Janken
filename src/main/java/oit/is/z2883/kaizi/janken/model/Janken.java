package oit.is.z2883.kaizi.janken.model;

public class Janken {
  private String myHand;
  private String cpuHand;
  private String result;

  public Janken(String myHand) {
    this.myHand = myHand;
    this.cpuHand = "Gu";
    this.result = judge();
  }

  private String judge() {
    if (myHand.equals("Gu")) {
      return "Draw";
    } else if (myHand.equals("Choki")) {
      return "You Lose";
    } else {
      return "You Win";
    }
  }

  public String getMyHand() {
    return myHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
