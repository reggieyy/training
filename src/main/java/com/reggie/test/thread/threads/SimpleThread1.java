package com.reggie.test.thread.threads;

/**
 * Created by reggie on 2017/5/8.
 */
public class SimpleThread1 implements Runnable {

  public void run() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("---->" + Thread.currentThread().getName());
  }
}
