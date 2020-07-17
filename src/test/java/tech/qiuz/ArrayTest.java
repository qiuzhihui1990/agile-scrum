package tech.qiuz;

import org.junit.Test;

public class ArrayTest {
  @Test
  public void testDymArray() {
    int[] nums = new int[10];

    for (int num : nums) {
      System.out.print(num);
    }
    System.out.println(nums.length);
    nums[0] = 2;
    System.out.println(nums.length);

    Integer[] nums2 = new Integer[10];
    for (Integer num : nums2) {
      System.out.println(num);
    }
    System.out.println(nums2.length);
    nums2[0] = new Integer(11);
    System.out.println(nums.length);
  }

  @Test
  public void testArrayAllocated() {
    int[] array = new int[3];
    Class clazz = array.getClass();
    System.out.println("array的父类是：" + array.getClass().getSuperclass());
    System.out.println("array的类名是：" + array.getClass().getName());
    System.out.println(clazz.getDeclaredFields().length);
    System.out.println(clazz.getDeclaredMethods().length);
    System.out.println(clazz.getDeclaredConstructors().length);
    System.out.println(clazz.getDeclaredAnnotations().length);
    System.out.println(clazz.getDeclaredClasses().length);

    System.out.println("array的类名是：" + new Integer[2].getClass().getName());
  }

  @Test
  public void testArrayClass() {
    int[] odIntArray = new int[10]; // od -> one-dimensional 一维
    int[][] tdIntArray = new int[10][10]; // td -> two-dimensional 二维
    System.out.println(odIntArray.getClass().getName());
    System.out.println(tdIntArray.getClass().getName());

    Integer[] odIntegerArray = new Integer[10];
    Integer[][] tdIntegerArray = new Integer[10][10];
    Integer[][] tdIntegerArray2 = new Integer[10][10];
    System.out.println(odIntegerArray.getClass().getName());
    System.out.println(tdIntegerArray.getClass().getName());

    // 同类型同维度的数组类型
    System.out.println(tdIntegerArray.getClass() == tdIntegerArray2.getClass());
    // 同维度不同类型数组比较，编译错误：不可比较类型
    // System.out.println(odIntArray.getClass() == odIntegerArray.getClass());
    // 相同类型不同维度，编译错误：不可比较类型
    // System.out.println(odIntegerArray.getClass() == tdIntegerArray.getClass());
  }

  @Test
  public void testArrayClassType() {
    Integer[] arr = new Integer[3];
    Class clazz = arr.getClass();
    System.out.println(clazz.getSimpleName());
    System.out.println(clazz.getCanonicalName());
    System.out.println(clazz.getName());
    System.out.println(int.class.getName());
    System.out.println(clazz.getSuperclass());
    System.out.println(clazz.getDeclaredFields().length);
    System.out.println(clazz.getDeclaredMethods().length);
    System.out.println(clazz.getDeclaredConstructors().length);
    System.out.println(clazz.getDeclaredAnnotations().length);
    System.out.println(clazz.getDeclaredClasses().length);
  }
}