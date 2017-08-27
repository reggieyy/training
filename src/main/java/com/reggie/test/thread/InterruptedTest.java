package com.reggie.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/8/25.
 */
public class InterruptedTest {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new SleepThread(),"sleep-thread-1");
    Thread thread2 = new Thread(new BusyThread(),"busy-thread-1");
    thread1.start();
    thread2.start();
    TimeUnit.SECONDS.sleep(5);
    thread1.interrupt();
    thread2.interrupt();
    System.out.println(thread1.getName()+"->"+thread1.isInterrupted());
    System.out.println(thread2.getName()+"->"+thread2.isInterrupted());

  }

  static class BusyThread implements Runnable {

    @Override
    public void run() {
      while (true){

      }
    }
  }

  static class SleepThread implements Runnable{

    @Override
    public void run() {
      while (true){
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }


}
