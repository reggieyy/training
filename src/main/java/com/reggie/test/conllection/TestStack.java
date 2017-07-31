package com.reggie.test.conllection;

import java.util.Stack;

/**
 * 测试栈
 * Created by reggie on 2017/7/31.
 *
 * push   pop/peek
 */
public class TestStack {

  public static void main(String[] args){
    Stack s = new Stack();
    s.add("qingtian");
    s.add("yaoyao");
    s.add("test");
    s.remove("yaoyao");
    System.out.println(s.search("test"));
    System.out.println(s.pop());
    System.out.println(s.pop());
  }

}
