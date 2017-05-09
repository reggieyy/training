package com.reggie.test.str;

/**
 * Created by reggie on 2017/5/7.
 * static为静态修饰，表明了该常量final_str在开始加载的时候就被赋值，可以修改
 * final代表了无法被重新赋值的最终final_str
 *
 */
public class FinalTest {

    private static final String final_str = "qingtian";

    public static void main(String[] args){
        System.out.println(final_str);
    }
}
