package tech.qiuz.bean;

import java.io.Serializable;

/**
 * @author zhuihui.qiu
 */
public class Employees implements Serializable{
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(final String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(final String lname) {
    this.lname = lname;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(final Long storeId) {
    this.storeId = storeId;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(final Long departmentId) {
    this.departmentId = departmentId;
  }

  private Long id;
  private String  fname;
  private String lname;
  private Long storeId;
  private Long departmentId;
}
