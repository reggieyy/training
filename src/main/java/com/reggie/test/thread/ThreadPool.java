package com.reggie.test.thread;

import com.reggie.test.thread.threads.SimpleThread1;
import com.reggie.test.thread.threads.SimpleThread2;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/5/8.
 *
 */
public class ThreadPool {

  public static void testCachedPool() {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for(int i=0;i<100;i++){
      cachedThreadPool.execute(new Thread(new SimpleThread2()));
    }
  }

  public static void testFixedPool() {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for(int i=0;i<100;i++){
      fixedThreadPool.execute(new Thread(new SimpleThread1()));
    }
  }

  public static void testScheduledThreadPool() {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    scheduledThreadPool.scheduleAtFixedRate(new Thread(new SimpleThread2()),3,3, TimeUnit.SECONDS);
  }

  public static void main(String[] args) {
//    testCachedPool();
//    testFixedPool();
    testScheduledThreadPool();
  }

}
