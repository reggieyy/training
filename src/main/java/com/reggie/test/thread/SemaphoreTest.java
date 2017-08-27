package com.reggie.test.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 线程并发控制测试
 * Created by reggie on 2017/8/27.
 */
public class SemaphoreTest {

  static Semaphore s = new Semaphore(10);

  public static void main(String[] args){
    for(int i=0;i<30;i++){
      new Thread(new TestThread(),"test-thread").start();
    }
    System.out.println("当前可用许可证数："+s.availablePermits());
    System.out.println("正在等待获取许可证的线程数："+s.getQueueLength());
    System.out.println("是否有线程正在等待许可证："+s.hasQueuedThreads());
//    System.out.println("");

  }

  static class TestThread implements Runnable {

    @Override
    public void run() {
      try {
        s.acquire();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("data is run ...");
        s.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
