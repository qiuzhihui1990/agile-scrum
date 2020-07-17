package tech.qiuz;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortAlgo {
  public static int length = 10;

  private int[] nums;

  @Before
  public void beforeMethod() {
    nums = new int[length];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = new Random().nextInt(100);
    }
  }

  @Test
  public void testBubbleSort() {
    System.out.println("before sort:" + Arrays.toString(nums));
    bubbleSort(nums);
    System.out.println("after sort:" + Arrays.toString(nums));
  }

  public static void bubbleSort(int[] nums) {
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = i; j > 0; j--) {
        if (less(nums[j], nums[j - 1])) {
          swap(nums, j, j-1);
        }
      }
    }
  }

  @Test
  public void testSelectionSort() {
    System.out.println("before sort:" + Arrays.toString(nums));
    selectionSort(nums);
    System.out.println("after sort:" + Arrays.toString(nums));
  }

  public static void selectionSort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int min = i;
      for (int j = i + 1; j < nums.length; j++) {
        if(nums[j] < nums[min]) {
          min = j;
        }
      }
      swap(nums, i, min);
      System.out.println(Arrays.toString(nums));
    }
  }

  @Test
  public void testInsectionSort() {
    System.out.println("before sort:" + Arrays.toString(nums));
    insertionSort(nums);
    System.out.println("after sort:" + Arrays.toString(nums));
  }

  public static void insertionSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      for (int j = i; j > 0 && less(nums[j], nums[j-1]); j--) {
        swap(nums, j , j-1);
      }
      System.out.println(Arrays.toString(nums));
    }
  }
  @Test
  public void testInsectionSort2() {
    System.out.println("before sort:" + Arrays.toString(nums));
    insertionSort2(nums);
    System.out.println("after sort:" + Arrays.toString(nums));
  }

  public static void insertionSort2(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      int temp = nums[i];
      int index = i;
      for (int j = i; j > 0 && less(temp, nums[j-1]); j--) {
        nums[j] = nums[j-1];
        index = j-1;
      }
      nums[index] = temp;
      System.out.println(Arrays.toString(nums));
    }
  }

  @Test
  public void testShellSort() {
    System.out.println("before sort:" + Arrays.toString(nums));
    shellSort(nums);
    System.out.println("after sort:" + Arrays.toString(nums));
  }

  public static void shellSort(int[] nums) {
    int h = 1;
    while (h < nums.length/3) h = 3*h + 1;
    while (h >= 1) {
      for (int i = h; i < nums.length; i++) {
        for (int j = i; j >=h && less(nums[j], nums[j-h]); j -=h) {
            swap(nums, j, j-h);
            System.out.println("h:" + h + "-" + Arrays.toString(nums));

        }
        System.out.println("h:" + h + "-" + Arrays.toString(nums));
      }
      h = h/3;
    }

  }

  public static void shellSort2(int[] nums) {
    int h = 1;
    while (h < nums.length / 3) {
      h = 3 * h + 1;
    }

    for (int i = h; i > 0; i--) {
      for (int j = 0; j + i < nums.length; j ++) {
        if (less(nums[j + i], nums[j])) {
          swap(nums, j, j+i);
        }
      }
      System.out.println(Arrays.toString(nums));
    }
  }

  private static boolean less(int num1, int num2) {
    return num1 < num2;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
