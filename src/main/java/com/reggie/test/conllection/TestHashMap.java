package com.reggie.test.conllection;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by reggie on 2017/5/8.
 */
public class TestHashMap {

  public static void easyPrint(){
    HashMap map = new HashMap();
    map.put("test1","123");
    map.put("test2","1234");
    Iterator it = map.keySet().iterator();
    while (it.hasNext()){
      System.out.println(map.get(it.next()));
    }
  }

  public static int testIndexFor(int h,int length){
    return h & (length-1);
  }

  public static void main(String[] args){
//    easyPrint();
//    int h = "15".hashCode();
    System.out.print(testIndexFor(18,16));
  }
}
