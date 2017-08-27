package com.reggie.test.thread;

/**
 * 两个线程交替执行
 * Created by reggie on 2017/8/25.
 */
public class AlterNateTest {

  static Boolean flag = true;
  static Object lock = new Object();

  public static void main(String[] args){
    new Thread(new Thread1(),"thread1-----").start();
    new Thread(new Thread2(),"thread2-----").start();
  }

  static class Thread1 implements Runnable {

    @Override
    public void run() {
      synchronized (lock){
          while (flag){
            System.out.println(Thread.currentThread().getName()+"--开始执行");
            flag = false;
            lock.notifyAll();
            try {
              lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

      }
    }
  }

  static class Thread2 implements Runnable {

    @Override
    public void run() {
      synchronized (lock){
          while (!flag){
            System.out.println(Thread.currentThread().getName()+"--开始执行");
            flag = true;
            lock.notifyAll();
            try {
              lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
      }
    }
  }

}
