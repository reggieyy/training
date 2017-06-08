package com.reggie.test.thread;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by reggie on 2017/6/2.
 * 测试队列的效率以及同步操作时候的隐藏问题
 */
public class QueueTake {

  /**
   * 创建一个普通的字符串队列
   */
  private static LinkedBlockingQueue<String> strQueue = new LinkedBlockingQueue<String>(3);


  /**
   * 插入线程
   */
  private static class addThread implements Runnable{

    private String localStr;

    public addThread(String str){
      this.localStr = str;
    }

    public void run() {
      try {
        strQueue.put(localStr);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 抓取线程
   */
  private static class getThread implements Runnable {

    public void run() {
      for(;;){
        if(strQueue != null && strQueue.size() > 0){
          try {
            TimeUnit.SECONDS.sleep(1);
            String a = strQueue.take();
            System.out.println("-----"+a);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

    }
  }

  public static void main(String[] args) throws InterruptedException {
//    LinkedBlockingQueue<String> strQueue1 = new LinkedBlockingQueue<String>(3);
//    strQueue1.add("qingtian test");
//    String a = strQueue1.take();
//    System.out.print(strQueue1.size());
    ExecutorService executor = Executors.newCachedThreadPool();
    ExecutorService executor1 = Executors.newCachedThreadPool();
    executor1.execute(new getThread());
    for(int i=0;i<1000;i++){
      executor.execute(new addThread("qingtian"+i));
    }


  }
}
