package com.reggie.test.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    Runnable r = () -> System.out.print("this is simple test!");
    r.run();

    List<String> list = new ArrayList<String>();
    list.add("qingtian");
    list.add("yaoyao");
    list.forEach(System.out::println);
    list.forEach((name) -> System.out.print(name + "; "));

    List<String> list1 = new ArrayList<>();
    list1.add("1");
    list1.add("1");
    list1.add("2");
    System.out.println();
    System.out.println(list1.isEmpty());
    list1.stream().distinct().forEach((name) -> System.out.print(name + "; "));
  }

}
