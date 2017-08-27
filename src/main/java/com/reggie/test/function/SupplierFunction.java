package com.reggie.test.function;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * 供给型
 * Created by reggie on 2017/8/13.
 */
public class SupplierFunction {

  public static List<String> supplierTest(int num,Supplier<String> supplier){
    List<String> list1 = new ArrayList<>();
    for(int i=0;i<num;i++){
      list1.add(supplier.get());
    }
    return list1;
  }

  public static void main(String[] args){
    List<String> list = supplierTest(5,() -> UUID.randomUUID().toString());
    list.forEach(System.out::println);
  }
}
