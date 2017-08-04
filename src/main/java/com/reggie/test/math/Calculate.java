package com.reggie.test.math;

/**
 * Created by reggie on 2017/8/3.
 */
public class Calculate {

  /**
   * 平方根
   * @param count
   * @return
   */
  public static float getN(int count){
    return (float) Math.sqrt(count);

  }
  public static void main(String[] args){
    System.out.println((int) getN(10));
    System.out.println(Math.log(10)/Math.log(2));//这个对应log2（10）
  }
}
