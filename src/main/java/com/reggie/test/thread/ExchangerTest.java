package com.reggie.test.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by reggie on 2017/8/27.
 */
public class ExchangerTest {

  private static final Exchanger<String> exgr = new Exchanger<String>();

  private static ExecutorService executor = Executors.newCachedThreadPool();

  public static void main(String[] args){
   executor.execute(new Runnable() {
     @Override
     public void run() {
       String A = "测试A";
       try {
         exgr.exchange(A);
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
     }
   });

   executor.execute(new Runnable() {
     @Override
     public void run() {
       String B = "测试B";
       try {
         String A = exgr.exchange(B);

         System.out.println("A和B是否一致："+A.equals(B)+"  A：" + A + "  B:" + B);
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
     }
   });
  }
}
