package com.reggie.test.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by reggie on 2017/8/5.
 */
public class TestCountDownLatch {

  private static CountDownLatch c = new CountDownLatch(3);

  public static class CountDown extends Thread{

    public void run(){
      System.out.println(Thread.currentThread().getName()+" prepare to count down..");
      c.countDown();
    }

  }

  public static void main(String[] args) throws InterruptedException {

    new Thread(new CountDown()).start();
    new Thread(new CountDown()).start();
    new Thread(new CountDown()).start();

    c.await();
    System.out.println("all works end..");
  }
}
