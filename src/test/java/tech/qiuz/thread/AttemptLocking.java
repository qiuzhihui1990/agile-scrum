package tech.qiuz.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
  private ReentrantLock lockk = new ReentrantLock();

  public void untimed() {
    boolean captured = lockk.tryLock();
    try {
      System.out.println("trylock():" + captured);
    } finally {
      if (captured) {
        lockk.unlock();
      }
    }
  }

  public void timed() {
   boolean captured = true;
   try {
     captured = lockk.tryLock(1, TimeUnit.SECONDS);
   } catch (InterruptedException e) {
     throw new RuntimeException(e);
   }
   try {
     System.out.println("tryLock(2, TimeUnit.SECONDS: " + captured);
   } finally {
     if (captured)
       lockk.unlock();
   }
  }

  public static void main(String[] args) throws InterruptedException {
    final AttemptLocking al = new AttemptLocking();
    al.untimed();
    al.timed();
    new Thread() {
      {setDaemon(true);}
      @Override
      public void run() {
        al.lockk.lock();
        System.out.println("acquired");
      }
    }.join();
    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    al.untimed();
    al.timed();
  }
}
