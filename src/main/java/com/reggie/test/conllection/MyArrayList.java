package com.reggie.test.conllection;

import java.util.Iterator;

/**
 * Created by reggie on 2017/7/30.
 * 自定义arraylist实现
 */
public class MyArrayList<E> {

  private Object[] elements;
  private int size;

  public MyArrayList(int length){
    elements = new Object[length];
  }

  public int size(){
    return size;
  }

  public void add(E e){
    elements[size++] = e;
  }

  public void add(int index,E e){
    System.arraycopy(elements, index, elements , index+1, size-index);
    elements[index] = e;
    size++;
  }

  public void remove(int index){
    int num = size-index-1;
    if(num > 0){
      System.arraycopy(elements, index+1, elements, index, num);
    }
    elements[--size] = null;
  }

  public E get(int index){
    return (E) elements[index];
  }

  public static void main(String[] args){
    MyArrayList list = new MyArrayList(10);
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add(2,"3+");
    list.remove(4);
    System.out.println("size:"+list.size());
    for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));
    }
  }
}
