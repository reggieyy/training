package com.reggie.test.algorithm;

/**
 * 冒泡排序
 * Created by reggie on 2017/8/7.
 */
public class TestBubbleSort {

  static int[] array = {10,9,8,7,6,5,4,3,1,2};

  public static void main(String[] args){
    int length = array.length;
    int m;
    for(int i=0;i<length;i++){
      System.out.print("第"+(i+1)+"次排序后的结果：");
      for(int a=0;a<array.length;a++){
        System.out.print(array[a]+",");
      }
      System.out.println();
      for(int j=length-1;j>i;j--){
        if(array[j] < array[j-1]){
          m = array[j-1];
          array[j-1] = array[j];
          array[j] = m;
        }
      }
    }



  }
}
