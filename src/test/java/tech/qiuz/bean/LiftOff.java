package tech.qiuz.bean;


public class LiftOff implements Runnable {
  public int getCountDown() {
    return countDown;
  }

  public void setCountDown(final int countDown) {
    this.countDown = countDown;
  }

  private int countDown = 10;
  private static int taskCount = 0;
  private final int id = taskCount++;
  private int priority ;

  public LiftOff(int countDown, int priority) {
    this.countDown = countDown;
    this.priority = priority;
  }

  public String status() {
    return this + "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + ").";
  }

  @Override
  public void run() {
    Thread.currentThread().setPriority(this.priority);
    while (true) {
      for (int i = 1; i< 100000; i++) {
        double d = (Math.PI + Math.E) / (double) i;
        if (i % 1000 == 0) {
        }
      }
      System.out.println(this);
      if (--countDown == 0) return;
    }

  }

  @Override
  public String toString() {
    return Thread.currentThread().toString();
  }
}