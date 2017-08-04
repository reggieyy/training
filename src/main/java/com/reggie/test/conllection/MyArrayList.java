package com.reggie.test.conllection;

import java.util.Iterator;

/**
 * Created by reggie on 2017/7/30.
 * 自定义arraylist实现
 */
public class MyArrayList<E> {

  /**
   * Object[]数组确定了ArrayList的线性结构
   */
  private Object[] elements;
  /**
   * 记录List中的对象个数（长度）
   */
  private int size;

  /**
   * 构造函数，创建数组的实例
   * @param length
   */
  public MyArrayList(int length){
    elements = new Object[length];
  }

  /**
   * 返回数组长度
   * @return
   */
  public int size(){
    return size;
  }

  /**
   * 普通的add方法，直接添加到这片连续空间的下个位置，由size作为下标
   * @param e
   */
  public void add(E e){
    elements[size++] = e;
  }

  /**
   * 指定位置的添加
   * 因为要插入在中间，那么为了确保下标的位置准确，需要将插入位置之后的元素全部移动1位
   * 直接通过arraycopy，长度直接用size-index，因为移动的时候需要包含index位置的原有
   * 元素
   * @param index
   * @param e
   */
  public void add(int index,E e){
    System.arraycopy(elements, index, elements , index+1, size-index);
    elements[index] = e;
    size++;
  }

  /**
   * 删除元素
   * 根据index来进行删除，和指定位置的插入类似，在删除的时候需要将删除元素后面的元素都向
   * 前移动一位，因为元素被删除所以移动元素的个数不包含index原有元素（size-index-1），
   * 最后将数组最后一位变为null（因为数据都前移了）
   * @param index
   */
  public void remove(int index){
    int num = size-index-1;
    if(num > 0){
      System.arraycopy(elements, index+1, elements, index, num);
    }
    elements[--size] = null;
  }

  /**
   * ArrayList查询快，下标能够快速找到对象在堆中内存空间
   * @param index
   * @return
   */
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
