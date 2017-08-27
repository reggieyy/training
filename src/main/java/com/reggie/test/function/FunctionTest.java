package com.reggie.test.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by reggie on 2017/8/9.
 */
public class FunctionTest {

  public static List<String> filter(List<String> fruit, Predicate<String> predicate){
    List<String> f = new ArrayList<>();
    for (String s : fruit) {
      if(predicate.test(s)){
        f.add(s);
      }
    }
    return f;
  }
  public static void main(String[] args) {
    List<String> fruit = Arrays.asList("香蕉", "哈密瓜", "榴莲", "火龙果", "水蜜桃");
    List<String> newFruit = filter(fruit, (f) -> f.length() == 2);
    System.out.println(newFruit);
  }
}
