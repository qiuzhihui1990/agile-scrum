package tech.qiuz.jvm;

import java.util.Arrays;

public class JavaCommand {
  public static void main(String[] args) {
    System.out.println(System.getenv());
    System.out.println(System.getProperties());
    System.out.println(System.getProperty("test"));
    System.out.println(Arrays.toString(args));
  }
}
