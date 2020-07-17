package tech.qiuz.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomicityTest implements Runnable {
  private AtomicInteger i = new AtomicInteger(0);
  private List<Integer> list = Collections.synchronizedList(new ArrayList<>());
  public int getValue() {
    return i.get();
  }
  public void evenIncrement() {
   i.addAndGet(2);
  }
  @Override
  public void run() {
    while (true)
      evenIncrement();
  }

  public static void main(String[] args) {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.err.println("Aborting");
        System.exit(0);
      }
    }, 5000);
    AutomicityTest st = new AutomicityTest();
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(st);
    while (true) {
      int val = st.getValue();
      if (val % 2 != 0) {
        System.out.println(val);
        System.exit(0);
      }
    }
  }
}
