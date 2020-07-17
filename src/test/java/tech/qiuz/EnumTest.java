package tech.qiuz;

import org.junit.Test;

import java.util.Arrays;

public class EnumTest {
  @Test
  public void testEnum() {
    Class<Explore> clazz = Explore.class;
    System.out.println(Arrays.toString(clazz.getGenericInterfaces()));
    System.out.println(Arrays.toString(clazz.getConstructors()));
    System.out.println(Arrays.toString(clazz.getMethods()));
    System.out.println(clazz.getSuperclass());

    Class<Enum> clazz1 = Enum.class;

    System.out.println(Arrays.toString(clazz1.getGenericInterfaces()));
    System.out.println(Arrays.toString(clazz1.getConstructors()));
    System.out.println(Arrays.toString(clazz1.getMethods()));
    System.out.println(clazz1.getSuperclass());
    ;
  }

  enum Explore { HERE, THERE}
}
