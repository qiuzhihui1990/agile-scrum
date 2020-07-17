package tech.qiuz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CollectionTest {
  @Test
  public void testCollection() {
    Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    Integer[] moreInts = {6, 7, 8, 9};
    collection.addAll(Arrays.asList(moreInts));
    Collections.addAll(collection, 11, 12, 13);
    List<Integer> list = Arrays.asList(15, 16, 17);
    list.add(18);
  }

  @Test
  public void testListIterator() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    ListIterator<Integer> listIterator = integers.listIterator();
    while (listIterator.hasNext()) {
      System.out.println(listIterator.next() + "n extIndex:" + listIterator.nextIndex() + " preIndex:" + listIterator.previousIndex());
    }
    Collections.synchronizedList(integers);
  }

  @Test
  public void testSet() {
    Set<Integer> sets = new TreeSet<>();
    Random rand = new Random(47);
    for (int i = 0; i < 10; i++) {
      sets.add(rand.nextInt(i+ 10));
    }
    System.out.println(sets);
    Set<Integer> sets1 = new TreeSet<>(Collections.reverseOrder());
  }

  @Test
  public void testQueue() {
    PriorityQueue queue = new PriorityQueue();
  }

  @Test
  public void testIterator() {

    test(Arrays.asList(1, 2, 3));
    Integer[] strings = {3, 4, 5};
    for(Integer t : strings) {
      System.out.println(t + " ");
    }
    try {
      throw new IllegalAccessException();
    } catch (Exception e) {
      StackTraceElement[] elements = e.getStackTrace();
      e.printStackTrace();
    }

  }

  static <T> void test(Iterable<T> lib) {
    for(T t : lib) {
      System.out.println(t + " ");
    }
  }

  @Test
  public void test(){
    LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
    map.put("1",1) ;
    map.put("2",2) ;
    map.put("3",3) ;
    map.put("4",4) ;
    map.put("5",5) ;
    System.out.println(map.toString());

  }
}
