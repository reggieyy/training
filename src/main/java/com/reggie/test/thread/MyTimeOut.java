package com.reggie.test.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/8/26.
 */
public class MyTimeOut {


  static Queue queue = new LinkedList();

  private synchronized String getName(long mills) throws InterruptedException {
    String result = null;
    long future = System.currentTimeMillis() + mills;
    long remaining = mills;
    while (queue.isEmpty() && remaining > 0) {
      wait(remaining);
      remaining = future - System.currentTimeMillis();
    }

    result = (String) queue.poll();
    return result != null ? result : "超时时间"+mills+"已经到达，退出...";
  }



  public static void main(String[] args) throws InterruptedException {
    MyTimeOut t = new MyTimeOut();
    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      queue.add("qingtian");
      System.out.println("add success!");
    }).start();
    System.out.println(t.getName(2000));


  }




}
