package com.reggie.test.str;

/**
 * Created by reggie on 2017/7/31.
 */
public class MyHashCode {

  public static Integer hash(String a){
    int hash = 0;
    if(hash == 0 && a.toCharArray().length > 0){
      char[] val = a.toCharArray();
      for(int i=0;i<a.length();i++){
        hash = hash*31+val[i];
      }
    }
    return hash;
  }

  public static void main(String[] args){
    System.out.println(hash("qingtian"));
    System.out.println("qingtian".hashCode());

  }
}
