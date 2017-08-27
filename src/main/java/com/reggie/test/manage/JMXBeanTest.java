package com.reggie.test.manage;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by reggie on 2017/8/25.
 */
public class JMXBeanTest {

  public static void main(String[] args){
    //获取java线程管理MXBean
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆信息
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
    //遍历线程信息，打印线程ID和名称
    for(ThreadInfo threadInfo : threadInfos){
      System.out.println("-->"+threadInfo.getThreadId()+"--"+threadInfo.getThreadName());
    }
  }

}
