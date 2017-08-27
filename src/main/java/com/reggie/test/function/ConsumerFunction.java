package com.reggie.test.function;

import java.util.function.Consumer;

/**
 * 消费型
 * Created by reggie on 2017/8/13.
 */
public class ConsumerFunction {

  public static void consumerTest(int num,Consumer<Integer> consumer){
    consumer.accept(num);
  }

  public static void main(String[] args){
    consumerTest(100,num -> System.out.println("一共有"+num+"个") );
  }

}
