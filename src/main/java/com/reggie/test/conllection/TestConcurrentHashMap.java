package com.reggie.test.conllection;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by reggie on 2017/6/6.
 */
public class TestConcurrentHashMap {

  public static void main(String[] args){
    ConcurrentHashMap map = new ConcurrentHashMap();
    map.put("1","qingtian");
    map.put("2","yaoyao");
    Iterator it = map.keySet().iterator();
    while (it.hasNext()){
      System.out.println(map.get(it.next())+"----");
    }
    System.out.println(map.get("1"));


    int size = 100;
    System.out.println(size >> 3);
  }
}
