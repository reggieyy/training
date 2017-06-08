package com.reggie.test.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by reggie on 2017/5/24.
 */
public class AtomicTest implements Runnable {

  private static AtomicInteger i = new AtomicInteger(0);

  public void run() {
    while (true){
      System.out.println(i.addAndGet(1));
    }
  }

  public static void main(String[] args){
    ExecutorService exector = Executors.newCachedThreadPool();
    exector.execute(new AtomicTest());
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println("exit....");
        System.exit(0);
      }
    }, 1000);
  }
}
