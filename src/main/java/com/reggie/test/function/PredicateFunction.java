package com.reggie.test.function;

import java.util.function.Predicate;

/**
 * 断言性
 * Created by reggie on 2017/8/13.
 */
public class PredicateFunction {

  public static boolean predicateFunction(int num,Predicate<Integer> predicate){
    return predicate.test(num);
  }

  public static void main(String[] args){
    System.out.println(predicateFunction(100,(f) -> f > 99));
  }

}
