package com.reggie.test.algorithm;

/**
 * 二分法查询
 * Created by reggie on 2017/8/5.
 */
public class TestBinarySearch {

  public static int[] data = {1,2,3,4,5,67,77,88,99,102,130,140,166};

  /**
   * 二分查询
   * @param key
   */
  public static int binarySearch(int key){
    int low = 0;
    int high = data.length-1;
    int mid;

    while (low <= high){
      mid = (high+low)/2;
//      mid = low + (high-low)*(key-data[low])/(data[high]-data[low]);
      if(key < data[mid]){
        high = mid -1;
      }else if(key > data[mid]){
        low = mid + 1;
      }else{
        return data[mid];
      }

    }
    return 0;

  }

  public static void main(String[] args){
    System.out.print(binarySearch(167));
  }
}
