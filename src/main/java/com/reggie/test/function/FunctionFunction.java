package com.reggie.test.function;

import java.util.function.Function;

/**
 * 函数型
 * Created by reggie on 2017/8/13.
 */
public class FunctionFunction {

  public static int functionTest(String str,Function<String,Integer> function){
    return function.apply(str);
  }

  public static void main(String[] args){
    functionTest("100",(f) -> Integer.parseInt(f));
  }
}
