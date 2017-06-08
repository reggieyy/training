package com.reggie.test.thread;

/**
 * Created by reggie on 2017/5/24.
 */
public class ThreadLocalTest {

  private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
    public Integer initialValue(){
      return 1;
    }
  };

  public int getNextNum(){
    seqNum.set(seqNum.get() + 1);
    return seqNum.get();
  }

  public static class TestClient extends Thread {
    private ThreadLocalTest t;

    public TestClient(ThreadLocalTest t){
      this.t = t;
    }

    public void run(){
      for (int i = 0; i < 3; i++) {
        //每个线程打出3个序列值
        System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
            + t.getNextNum() + "]");
      }
    }
  }

  public static void main(String[] args){
    ThreadLocalTest tt = new ThreadLocalTest();
    TestClient tc1 = new TestClient(tt);
    TestClient tc2 = new TestClient(tt);
    tc1.start();
    tc2.start();
  }
}
