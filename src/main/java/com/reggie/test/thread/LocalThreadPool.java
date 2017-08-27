package com.reggie.test.thread;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.enumerate;
import static java.lang.Thread.interrupted;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 伸缩线程池
 * Created by reggie on 2017/8/27.
 */
public class LocalThreadPool {

  //工作线程数,默认为10
  private int workThreadCount = 10;

  private List<WorkThread> workThreadList = new ArrayList();

  //定义一个链表作为任务队列
  private Queue queue = new LinkedList();

  //连接池是否关闭标志位，默认关闭
  private boolean isClosed = false;

  //活动线程数
  private int exeThreadCount = 0;

  /**
   * 返回线程池的状态
   * @return
   */
  public boolean isClosed(){ return isClosed;}

  /**
   * 构造函数用于声明并启动一个线程池
   * @param workThreadCount
   */
  public LocalThreadPool(int workThreadCount){
    this.workThreadCount = workThreadCount;
  }

  /**
   * 启动线程池
   */
  public void runPool(){
    isClosed = true;
    createWorkThreads();
  }

  /**
   * 关闭线程池
   */
  public synchronized void closePool() throws InterruptedException {
    synchronized (this){
      isClosed = false;
      notifyAll();
    }
    Thread[] threads = new Thread[activeCount()];
    int count = enumerate(threads);
    for(int i=0;i<count;i++){
      threads[i].join();
    }
  }

  /**
   * 创建工作线程
   */
  public void createWorkThreads(){
    for(int i=0;i<workThreadCount;i++){
      WorkThread workThread = new WorkThread(i);
      Thread thread = new Thread(workThread,"localThreadPool-workThread-"+i);
      workThreadList.add(workThread);
      thread.start();
    }
  }

  public void deleteWorkThread(int num) throws InterruptedException {
    int count = 0;
    synchronized (this){
      while (count < num) {
        WorkThread workThread = (WorkThread) workThreadList.get(count);
        workThread.shutDown();
        count++;
      }
    }
    workThreadCount -= count;
  }

  /**
   * 执行的代码实际作用是讲任务放入队列中，接下来的工作线程池接管
   * @param job
   */
  public synchronized void execute(Runnable job){
    queue.offer(job);
    //唤醒工作线程来接管任务
    notifyAll();

  }

  public synchronized Runnable getJob(int threadID) throws InterruptedException {
    if(queue.size() == 0){//如果队列中没有任务，线程进入等待
      System.out.println("线程"+threadID+"等待执行任务");
      wait();
    }
    System.out.println("线程"+threadID+"开始执行任务");
    Runnable job = (Runnable) queue.poll();
    return job;
  }





  /**
   * 工作线程内部类
   */
  private class WorkThread implements Runnable{

    //线程ID，用于标记工作线程的唯一标识
    private int id;
    private boolean flag = true;

    public WorkThread(int id){
      this.id = id;
    }

    /**
     * 从队列中获取一个任务并执行
     */
    @Override
    public void run() {

      //只要没有终止，则始终循环执行
      while (flag){
        try {
          Runnable job = getJob(id);
          if(job != null){
            job.run();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
      System.out.println("thread-"+id+" is stop ..............................");
    }

    public void shutDown(){
      flag = false;
    }
  }

  static class TestJob implements Runnable{

    private int id;

    public TestJob(int id){
      this.id = id;
    }

    @Override
    public void run() {
      System.out.println("TestJob"+id+" is run ...");
    }

  }

  public static void main(String[] args) throws InterruptedException {
    LocalThreadPool localThreadPool = new LocalThreadPool(10);
    localThreadPool.runPool();
    localThreadPool.deleteWorkThread(3);
    for(int i=0;i<100;i++){
      localThreadPool.execute(new TestJob(i));
    }
//    localThreadPool.closePool();
//    System.out.println("线程池当前状态"+localThreadPool.isClosed);
  }

}
