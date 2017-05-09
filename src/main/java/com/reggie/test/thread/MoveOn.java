package com.reggie.test.thread;

import com.reggie.test.thread.threads.SimpleThread1;
import com.reggie.test.thread.threads.SimpleThread2;
import com.reggie.test.thread.threads.SimpleThread3;

/**
 * Created by reggie on 2017/5/8.
 * 线程按照固定的顺序进行执行，通过join来进行，join会在线程执行这一行代码后再进行后续代码的执行
 */
public class MoveOn {

  public static void main(String[] args) throws InterruptedException {
    Runnable r1 = new SimpleThread1();
    Runnable r2 = new SimpleThread2();
    Runnable r3 = new SimpleThread3();
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    Thread t3 = new Thread(r3);
    t1.start();
    t1.join();
    t2.start();
    t2.join();
    t3.start();
  }
}
