package com.reggie.test.thread;

import com.reggie.test.thread.threads.ThreadOne;
import com.reggie.test.thread.threads.ThreadTwo;

/**
 * Created by reggie on 2017/5/7.
 * 两个线程交替执行，通过synchronized来对obj进行同步操作，确保只有一个线程能够操作obj
 * flag对所有线程可见，变更状态来让其他线程进行判断
 * wait和notify让当前线程等待并唤醒其他线程
 */
public class ChangeRun {

    public static Object obj = new Object();
    public static volatile boolean flag = false;

    public static void main(String[] args){

        Runnable one = new ThreadOne();
        Runnable two = new ThreadTwo();
        Thread t1 = new Thread(one);
        Thread t2 = new Thread(two);
        t1.start();
        t2.start();


    }
}
