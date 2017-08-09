package com.reggie.test.algorithm;

/**
 * 排序算法
 * Created by reggie on 2017/8/8.
 */
public class TestSort {

  public static void main(String[] args){
    int[] array = {3,2,1};
//    TestSort sort_bubble = new TestSort(array);
//    sort_bubble.bubbleSort();
//    System.out.println("冒泡排序结果：");
//    sort_bubble.output();
//
//    System.out.println();
//
//    TestSort sort_simpleSelectSort = new TestSort(array);
//    sort_simpleSelectSort.simpleSelectSort();
//    System.out.println("选择排序结果：");
//    sort_simpleSelectSort.output();
//
//    System.out.println();
//
    TestSort sort_insertSort = new TestSort(array);
    sort_insertSort.insertSort();
    System.out.println("插入排序结果：");
    sort_insertSort.output();
//
    System.out.println();

    int[] a= {1,10,2,9,3,2,4,7,5,6};
    TestSort sort_quickSort = new TestSort(a);
    sort_quickSort.quickSort(a,0,a.length-1);
    System.out.println("快速排序结果：");
    sort_quickSort.output();

  }

  private int[] _array;

  public void output(){
    for(int i=0;i<_array.length;i++){
      System.out.print(_array[i]+",");
    }
  }

  public TestSort(int[] array){
    _array = array;
  }

  /**
   * 快速排序
   *
   * @param array
   * @param start
   * @param end
   */
  public void quickSort(int[] array,int start,int end){
    if(start > end){
      return;
    }
    int mid = divide(array,start,end);
    quickSort(array,start,mid-1);
    quickSort(array,mid+1,end);
  }

  public int divide(int[] array,int start,int end){
    int target = array[end];
    while (start < end){
      //从左边开始遍历
      while (start < end && array[start] < target){
        start++;
      }
      if(start < end){//交换位置后切换到右边
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        end--;
      }
      while (start < end && array[end] > target){
        end--;
      }
      if(start < end){
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        start++;
      }
    }
    return end;
  }

  /**
   * 插入排序
   * 3 2 1
   * 2 3 1
   *
   */
  public void insertSort(){
    int i,j,target;
    for(i=1;i<_array.length;i++){
      target = _array[i];
      j=i;
      while (j>0 && target < _array[j-1]){
        _array[j] = _array[j-1];
        j--;
      }
      _array[j] = target;
    }
  }

  /**
   * 选择排序
   */
  public void simpleSelectSort(){
    for(int i=0;i<_array.length;i++){

      for(int j=i+1;j<_array.length;j++){
        if(_array[i]>_array[j]){//如果找到小于_array[i]的值就发生交换
          int m = _array[i];
          _array[i] = _array[j];
          _array[j] = m;
          break;
        }
      }

    }
  }


  /**
   * 冒泡排序
   */
  public void bubbleSort(){
    boolean flag = true;
    for(int i=0;i<_array.length && flag;i++){
      flag = false;
      for(int j=_array.length-1;j>i;j--){
        if(_array[j-1] > _array[j]){
          int m = _array[j];
          _array[j] = _array[j-1];
          _array[j-1] = m;
          flag = true;
        }
      }
    }
  }

}
