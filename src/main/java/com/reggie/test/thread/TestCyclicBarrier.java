package com.reggie.test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by reggie on 2017/8/5.
 */
public class TestCyclicBarrier {

  private static CyclicBarrier c = new CyclicBarrier(3);

  public static class CycB extends Thread {
    public void run(){
      try {
        System.out.println(Thread.currentThread().getName()+"等待中...");
        c.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      System.out.print(Thread.currentThread().getName()+"出来了...");
    }
  }

  public static void main(String[] args){
    new Thread(new CycB()).start();
    new Thread(new CycB()).start();
    new Thread(new CycB()).start();

  }
}
