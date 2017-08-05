package com.reggie.test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by reggie on 2017/8/6.
 */
public class lamda {

  public static int test(int i,int j){
    return i+j;
  }

  public static void main(String[] args){
    new Thread(() -> test(1,3)).start();


  }

}
