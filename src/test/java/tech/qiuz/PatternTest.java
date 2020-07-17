package tech.qiuz;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class PatternTest {
  @Test
  public void test() {
    String content = "{\"10d\": [], \"1cc\":[]}";
    String decryptContent = StringUtils.replaceAll(content, "0d\":(\\s)*\\[\\]", "0d\":[--]");
    System.out.println(decryptContent);
    decryptContent = StringUtils.replaceAll(decryptContent, "(\\s)*\\[\\]", "{}");
    System.out.println(decryptContent);
    decryptContent = StringUtils.replaceAll(decryptContent, "0d\":\\[--\\]", "0d\":[]");
    System.out.println(decryptContent);
  }
}
