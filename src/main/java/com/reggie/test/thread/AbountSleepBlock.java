package com.reggie.test.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/5/27.
 */
public class AbountSleepBlock implements Runnable {

  public void run(){
    try{
      TimeUnit.SECONDS.sleep(100);
    }catch(InterruptedException e) {
      System.out.println("InterruptedException !!!!");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Future<?> f = Executors.newCachedThreadPool().submit(new AbountSleepBlock());
    TimeUnit.MICROSECONDS.sleep(100);
    System.out.println("interrputing---->");
    f.cancel(true);
    System.out.println("interrputing sent to---->");
  }

}
