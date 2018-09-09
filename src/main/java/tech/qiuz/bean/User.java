package tech.qiuz.bean;

/**
 * @author zhuihui.qiu
 */

public class User {
  private String email;
  private String password;
  private String name;
  private int teamId;

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(final int teamId) {
    this.teamId = teamId;
  }
}
