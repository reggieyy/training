package com.reggie.test.thread.threads;

/**
 * Created by reggie on 2017/5/8.
 */
public class SimpleThread2 implements Runnable {

  @Override
  public void run() {
    System.out.println("---->" + Thread.currentThread().getName());
  }
}
