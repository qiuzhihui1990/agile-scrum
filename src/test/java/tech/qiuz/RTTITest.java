package tech.qiuz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RTTITest {
  @Test
  public void test() {
    Class<Integer> ic = int.class;
    System.out.println(ic);
    List<Integer> list = new ArrayList<Integer>();
    System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
    System.out.println(List.class.isInstance(list));;
  }
}
