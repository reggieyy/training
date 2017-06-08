package com.reggie.test.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by reggie on 2017/6/5.
 * 1.定义一个线程池，内部工作线程的数量由线程初始化决定
 * 2.使用lock来确保同步
 * 3.支持线程池的关闭
 */
public class EasyThreadPool extends ThreadGroup {

  /**
   * 使用linkedList作为
   */
  private LinkedList blockingQueue;

  /**
   * 线程池状态
   */
  private boolean shutDown;

  /**
   * 工作线程数
   */
  private int count;

  /**
   * 并发使用的锁
   * 主要引发await和signAll
   */
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  /**
   * 构造函数，用于初始化线程等待池大小和启动线程池
   */
  public EasyThreadPool(int count){
    super("EasyThreadPool");//实现父类构造器
    setDaemon(true);//设置为后台守护线程
    this.shutDown = true;
    this.blockingQueue = new LinkedList();
    if(count > 0){
      this.count = count;
    }else{
      this.count = 20;
    }
    startPool();
  }

  private void stopPool(){
    synchronized (this){
      shutDown = true;
      condition.signalAll();
    }
    Thread[] threads = new Thread[activeCount()];
    int count = enumerate(threads);
    for(int i=0;i<count;i++){
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 启动线程池
   */
  private void startPool(){
    for(int i=0;i<count;i++){
      new WorkThread(i).start();
    }
  }

  private void execute(Runnable task){
    lock.lock();
    try{
      this.blockingQueue.add(task);
      condition.signalAll();
    }finally {
      lock.unlock();
    }
  }
  /**
   * 获取任务
   * @param threadID
   * @return
   */
  private Runnable getTask(final int threadID) throws InterruptedException {
    lock.lock();
    try{
      if(shutDown){
        System.out.println("线程池没有开启，无法执行任务.....");
        return null;
      }
      while(this.blockingQueue.size() == 0){
        System.out.println("工作线程"+threadID+"等待任务");
        condition.await();//如果没有任务，await讲hold住任务直到队列中有任务进入唤醒线程去接收

      }
      System.out.println("工作线程"+threadID+"开始执行任务");
      Runnable task = (Runnable) this.blockingQueue.removeFirst();
      return task;
    }finally {
      lock.unlock();
    }

  }

  /**
   * 工作线程
   */
  private class WorkThread extends Thread {

    /**
     * 工作线程的ID
     */
    private int id;

    public WorkThread(final int id){
      this.id = id;
    }

    /**
     * 工作线程从队列获取任务并执行任务
     */
    public void run() {
      while (!interrupted()){
        Runnable task = null;
        try {
          task = getTask(id);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(null == task){
          return;
        }
        task.run();
      }
    }

  }

  public static void main(String[] args){
    EasyThreadPool epool = new EasyThreadPool(1);
    for(int i=0;i<1;i++){
      epool.execute(testTask(i));
    }
  }

  public static Runnable testTask(final int id){
    return new Runnable() {
      public void run() {
        System.out.println("执行内容："+Thread.currentThread().getName()+"---"+id);
      }
    };
  }

}
