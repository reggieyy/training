package com.reggie.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/8/25.
 */
public class ThreadState {

  public static void main(String[] args){
    new Thread(new TimeWating(),"TimeWating-test").start();
    new Thread(new Wating(),"WatingTest").start();
    new Thread(new Blocked(),"BlockedTest-1").start();
    new Thread(new Blocked(),"BlockedTest-2").start();
  }

  static class TimeWating implements Runnable {

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

  static class Wating implements Runnable {

    @Override
    public void run() {
      while (true){
        synchronized (Wating.class){
          try {
            Wating.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  static class Blocked implements Runnable {

    @Override
    public void run() {
      synchronized (Blocked.class){
        while (true)
          try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
      }
    }
  }

}
