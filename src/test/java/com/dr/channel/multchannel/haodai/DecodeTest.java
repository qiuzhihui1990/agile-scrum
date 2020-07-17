package com.dr.channel.multchannel.haodai;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class DecodeTest {

  public static void main(String[] args) throws UnsupportedEncodingException {
    String str = "方金成";
    byte[] test = str.getBytes(Charset.defaultCharset());
    for (byte t :test) {
      System.out.println(Integer.valueOf(t));
    }
    System.out.println("....");
    byte[] test2 = str.getBytes("UNICODE");
    for (byte t :test2) {
      System.out.println(Integer.valueOf(t));
    }
    System.out.println(test2);
    String myString = "\u65b9\u91d1\u6210";
    System.out.println(myString);
  }
}
