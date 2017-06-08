package com.reggie.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import sun.jvm.hotspot.utilities.WorkerThread;

/**
 * Created by reggie on 2017/6/1.
 */
public class MyThreadPool extends ThreadGroup {

  /**
   * 使用LinkedBlockingQueue阻塞队列，用于接收任务
   */
  private ConcurrentLinkedQueue blockingQueue;
  private boolean isClosed = false;  //线程池是否关闭

  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  /**
   * 线程执行器
   */
  private ExecutorService executor;

  private int size;

  /**
   * 构造函数:初始化线程池大小
   * @param size
   */
  public MyThreadPool(int size){
    super("myThreadPool");
    setDaemon(true);
    this.size = size;
    blockingQueue = new ConcurrentLinkedQueue();
    startMyThreadPool();
  }


  /**
   * 关闭线程池
   */
  private void stopMyThreadPool(){
    synchronized (this) {
      isClosed = true;
      condition.signalAll();			//唤醒所有还在getTask()方法中等待任务的工作线程
    }
    Thread[] threads = new Thread[activeCount()]; //activeCount() 返回该线程组中活动线程的估计值。
    int count = enumerate(threads); //enumerate()方法继承自ThreadGroup类，根据活动线程的估计值获得线程组中当前所有活动的工作线程
    for(int i =0; i < count; i++) { //等待所有工作线程结束
      try {
        threads[i].join();	//等待工作线程结束
      }catch(InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }


  /**
   * 启动线程池
   */
  private void startMyThreadPool(){
    for(int i=0;i<size;i++) {
      new WorkerThread(i).start();
    }
  }

  public void execute(Runnable task) throws InterruptedException {
    lock.lock();
    try{
      if(task != null){
        blockingQueue.add(task);
        condition.signalAll();
      }
    } finally {
      lock.unlock();
    }

  }

  private Runnable getTask(int threadid) throws InterruptedException {
    lock.lock();
    try {
      while(blockingQueue.size() == 0) {
        if(isClosed) return null;
        System.out.println("工作线程"+threadid+"等待任务...");
        condition.await();				//如果工作队列中没有任务,就等待任务
      }
      System.out.println("工作线程"+threadid+"开始执行任务...");
      Runnable task = (Runnable) blockingQueue.poll();
      System.out.println("队列长度============="+blockingQueue.size());
      return task;
    } finally {
      lock.unlock();
    }
  }

  private class WorkerThread extends Thread{
    private int id;

    public WorkerThread(int id){
      this.id = id;
    }

    public void run(){
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


  public static void main(String[] args) throws InterruptedException {
    MyThreadPool threadPool = new MyThreadPool(3); //创建一个有个3工作线程的线程池
    //运行任务
    for (int i = 0; i <=40 ; i++) { //创建6个任务
//      TimeUnit.MICROSECONDS.sleep(300);
      threadPool.execute(createTask(i));
    }
//    threadPool.waitFinish(); //等待所有任务执行完毕
//    threadPool.stopMyThreadPool(); //关闭线程池

  }

  private static Runnable createTask(final int taskID) {
    return new Runnable() {
      public void run() {
        System.out.println("Task" + taskID + "开始");
        System.out.println("Hello world");
        System.out.println("Task" + taskID + "结束");
      }
    };
  }
}
