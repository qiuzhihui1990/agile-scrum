package tech.qiuz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SecurityManager {
  public static void main(String[] args) {
    String s;
    try {
      FileReader fr = new FileReader(new File("E:\\test.txt"));
      BufferedReader br = new BufferedReader(fr);
      while ((s = br.readLine()) != null)
        System.out.println(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
