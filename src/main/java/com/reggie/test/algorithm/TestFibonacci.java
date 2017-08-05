package com.reggie.test.algorithm;

/**
 * 斐波拉契数列实践
 * Created by reggie on 2017/8/5.
 */
public class TestFibonacci {

  /**
   * 传统模式，根据后一个数为前面两个数之和来计算
   * 0 1 2 3 5 8 13
   * @param count
   */
  public static void normal(int count){
    int[] result = new int[count+1];
    result[0] = 0;
    result[1] = 1;
    System.out.print("传统方式数列：0 1 ");
    for(int i=2;i<=count;i++){
      result[i] = result[i-1] + result[i-2];
      System.out.print(result[i]+" ");
    }
    System.out.println();
    System.out.println("传统方式计算结果："+result[count]);
  }

  /**
   * 迭代模式
   * @param count
   * @return
   */
  public static int stack(int count){
    if(count < 2){
      return count == 0 ? 0 : 1;
    }
    return stack(count-1) + stack(count-2);
  }


  public static void main(String[] args){
    normal(40);
    System.out.println("迭代模式计算结果："+stack(40));
  }
}
