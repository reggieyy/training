package com.reggie.test.thread;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/5/24.
 */
public class CancelThreadTest implements Runnable {



  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();
    Future<?> f = executor.submit(new CancelThreadTest());
//    TimeUnit.SECONDS.sleep(100);
    f.cancel(true);
    System.out.println("isdone:"+f.isDone());
    System.out.println("iscanceled:"+f.isCancelled());
    executor.shutdown();
  }

  public void run() {
    try{
      for(int i=0;i<100000;i++){
        System.out.println(Thread.currentThread().getName()+i);
      }
    } catch(Exception e){
      System.out.println("inerrupt-----");
    }

  }
}

