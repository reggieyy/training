package com.reggie.test.thread.threads;

import com.reggie.test.thread.ChangeRun;

/**
 * Created by reggie on 2017/5/7.
 */
public class ThreadTwo implements Runnable {

    public void run() {
        synchronized (ChangeRun.obj){
            for(int i=0;i<=10;i++) {
                System.out.println("this is two run ->" + i);
                if(ChangeRun.flag){
                    ChangeRun.flag = false;
                    ChangeRun.obj.notify();
                    try {
                        ChangeRun.obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
