package tech.qiuz;

import org.junit.Test;

public class InstanceOfTest {
  @Test
  public void testInstance() {
    Integer i = null;
    if (i instanceof Integer) {
      System.out.println("true");
    }

  }
}
