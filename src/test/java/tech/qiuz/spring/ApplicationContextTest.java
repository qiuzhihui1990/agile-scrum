package tech.qiuz.spring;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {
  @Test
  public void testAppCon() {
    FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("bean.xml");
  }
}
