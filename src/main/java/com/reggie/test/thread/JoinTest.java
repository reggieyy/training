package com.reggie.test.thread;

import com.reggie.test.thread.AlterNateTest.Thread1;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/8/26.
 */
public class JoinTest {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new Thread1(),"thread1");
    Thread thread2 = new Thread(new Thread2(thread1),"thread2");
    thread1.start();
    thread2.start();

  }

  static class Thread1  implements Runnable {



    @Override
    public void run() {
      try {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(" this is thread1 ...");
//        Thread.currentThread().join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }


  static class Thread2 implements Runnable {

    private Thread thread;

    public Thread2(Thread thread){
      this.thread = thread;
    }

    @Override
    public void run() {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(" this is thread2 ...");
    }
  }

}
