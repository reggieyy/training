package com.reggie.test.thread;

/**
 * Created by reggie on 2017/8/25.
 */
public class SynchornizedTest {

  private volatile String aaa = "jiugig";

  public static void main(String[] args){
    synchronized (SynchornizedTest.class){
      String a = "1";
      System.out.println(a);
    }

  }

}
