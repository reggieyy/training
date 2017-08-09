package com.reggie.test.function;

/**
 * Created by reggie on 2017/8/9.
 */
public class FunctionTest {

  @FunctionalInterface
  interface Predicate<T> {
    boolean test(T t);
  }
  /**
   * 执行Predicate判断
   *
   * @param age       年龄
   * @param predicate Predicate函数式接口
   * @return          返回布尔类型结果
   */
  public static boolean doPredicate(int age, Predicate<Integer> predicate) {
    return predicate.test(age);
  }

  public static void main(String[] args) {
    boolean isAdult = doPredicate(20, x -> x < 18);
    System.out.println(isAdult);
  }
}
