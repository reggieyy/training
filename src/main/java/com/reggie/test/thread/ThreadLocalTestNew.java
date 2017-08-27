package com.reggie.test.thread;

import java.lang.management.ThreadInfo;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/8/26.
 */
public class ThreadLocalTestNew {

  static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) throws InterruptedException {
    new Thread(new TackThread(),"tackThread").start();
//    for (int i=0;i<10;i++){
//
//      new Thread(() -> threadLocal.set(threadLocal.get()+1));
//    }
//    TimeUnit.SECONDS.sleep(10);
//    System.out.println(threadLocal.get());
  }

  static class TackThread implements Runnable{

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
      threadLocal.set(2);
      for (int i=0;i<98;i++){

        threadLocal.set(threadLocal.get()+1);
      }
      System.out.println(threadLocal.get());
    }
  }



}
