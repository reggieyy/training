package com.reggie.test.algorithm;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * KMP模式匹配算法
 * Created by reggie on 2017/8/1.
 */
public class TestKMP {

  private static int[] next;

  /**
   * 计算next数组
   * 下面的描述中，最终值为前缀和后缀相同部分的长度
   * 原理是截取子串中不同长度的字符串，计算出这一部分字符串中相同前缀后缀的长度
   * @param subStr
   *
   */
  public static void makeNext(String subStr){
    int length = subStr.length();
    next = new int[length];
    for(int i=0;i<length;i++){
      String sub_use = subStr.substring(0,i+1);
      next[i] = 0;
      int this_length = sub_use.length();
      for(int j=this_length-1;j>0;j--){
        String pre_str = sub_use.substring(0,j);
        String suf_str = sub_use.substring(this_length-j,this_length);
        if(pre_str.equals(suf_str)){
          next[i] = pre_str.length();
          break;
        }
      }
    }
//    System.out.println(subStr);
//    for(int f=0;f<next.length;f++){
//      System.out.print(next[f]);
//    }
  }

  /**
   * 获取next数组
   * 外层循环是逐步向后取位（主要靠kmp计算出来的偏移量）
   * 内层循环是比较从第i个元素开始，主串和子串从第j=0开始每一个字符是否一致，如果一致continue下去，如果不一致，
   * 则计算偏移量break出循环，给外层i赋值进行偏移，需要注意的是建立了一个过度元素k，用于解决从每个i开始取char
   * 字符的时候不影响i本身的值
   * @param mainStr
   * @param subStr
   */
  public static Boolean kmp(String mainStr, String subStr){
    Boolean flag = false;
    makeNext(subStr);
    for(int i=0;i<mainStr.length();i++){
      int k = i;
      for(int j=0;j<subStr.length();j++){
        if(mainStr.charAt(k) != subStr.charAt(j)){
//          i = i+(j+1)-next[j]-1;
          i = i + j - next[j];
          break;
        }
        if(j == subStr.length()-1){
          flag = true;
        }
        k++;
      }
    }
    return flag;
  }

  public static void main(String[] args){
    Boolean flag = kmp("KQWJK1ABCADBCAJSDAKU","ABCABC");
    System.out.println("---->"+flag);
  }
}
