package com.reggie.test.thread.threads;

/**
 * Created by reggie on 2017/5/8.
 */
public class SimpleThread3 implements Runnable {

  public void run() {
    System.out.println("---->" + Thread.currentThread().getName());
  }
}
