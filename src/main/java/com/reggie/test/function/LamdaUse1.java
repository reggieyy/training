package com.reggie.test.function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reggie on 2017/8/10.
 */
public class LamdaUse1 {

  static Map<String,Map<String,Object>> map = new HashMap<>();

  public static void main(String[] args){
    Map<String,Object> map1 = new HashMap<>();
    Map<String,Object> map2 = new HashMap<>();
    map1.put("qingtian","lamda");
    map1.put("yaoyao","lamda--");
    map.put("tag",map1);
    map.keySet().stream().filter((key) -> key.equals("tag")).forEach((key) -> {
      Map<String,Object> subMap = map.get(key);
      subMap.keySet().stream().filter((subKey) -> subKey.equals("yaoyao")).forEach(
          (subKey) -> {
            System.out.print(subMap.get(subKey));
          }
      );
    });
  }
}
