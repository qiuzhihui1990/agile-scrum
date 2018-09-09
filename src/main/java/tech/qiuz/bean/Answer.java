package tech.qiuz.bean;

/**
 * @author zhuihui.qiu
 */

public class Answer {
  private Long aid;
  private Long mood;
  private String message;

  public Long getAid() {
    return aid;
  }

  public void setAid(final Long aid) {
    this.aid = aid;
  }

  public Long getMood() {
    return mood;
  }

  public void setMood(final Long mood) {
    this.mood = mood;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
