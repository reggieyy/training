package com.reggie.test.conllection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by reggie on 2017/7/28.
 * 简单自定义HashMap
 */
public class MyHashMap<K,V> {

  /**
   * 默认长度，31其实是java中hashcode计算时候的一个参数，这里使用也是确保求余计算能够正常得到整数
   */
  private final int length = 31;
  /**
   * Entry数组是HashMap的第一层，也是最基本的结构
   */
  private Entry<K,V>[] table;
  /**
   * 全局size是用于快速记录map中元素的个数
   */
  private Integer size;

  /**
   * 构造器，主要用于创建数组实例
   */
  public MyHashMap(){
    size = 0;
    table = new Entry[length];
  }

  /**
   * put方法
   * 1.计算存储位置
   * 2.如果位置没有对象，则存入，return
   * 3.如果位置有对象，则看对象的key是否和要创建元素的key相同，如果相同则覆盖，如果不同则加入到上一个元素的next位置
   * 4.需要注意的是，如果数组位置存放的不是该元素，则要进行链表的循环
   * @param key
   * @param value
   */
  public void put(K key,V value){
    size++;
    int i = key.hashCode()%length;
    Entry<K,V> e = new Entry<K, V>(key,value);
    //1.根据hash值找到对应的位置，如果没有元素，则新增
    if(table[i] == null){
      table[i] = e;
      return;
    }
    //2.如果hash值对应位置已经存在元素，那么就视为hash碰撞，此时需要进行判断，如果key相同则更新，如果不同则写入到链表中
    Entry<K,V> entry_exist = table[i];
    while (entry_exist != null) {
      if(entry_exist.key == key){
        entry_exist.value = e.value;
        size--;
        break;
      }
      if(entry_exist.next == null){
        entry_exist.next = e;
        break;
      }
      entry_exist = entry_exist.next;
    }
  }

  /**
   * remove方法
   * 找到数组位置，进行key值得比对，如果相同，则验证该元素是否有前置元素，如果没有，则是数组首位，直接置为null（e.next肯定是null），
   * 如果该元素有前置元素，那么前置元素的next直接用该元素后置元素替换。
   * @param key
   */
  public void remove(K key){
    int i = key.hashCode()%length;
    Entry<K,V> e = table[i];
    Entry<K,V> prev = null;
    while (e != null){
      if(e.key == key){
        if(prev == null){
          table[i] = e.next;
        }else{
          prev.next = e.next;
        }
        size--;
        break;
      }
      prev = e;
      e = e.next;
    }

  }

  /**
   * get方法
   * 通过存储位置找寻对象，如果数组找不到则进去链表，通过next循环
   * @param key
   * @return
   */
  public V get(K key){
    int i = key.hashCode()%length;
    Entry<K,V> e = table[i];
    while (e != null){
      if(e.key == key){
        return e.value;
      }
      e = e.next;
    }
    return null;
  }

  /**
   * 返回map长度
   * @return
   */
  public Integer size(){
    return size;
  }

  /**
   * keySet方法
   * 用于获取能够被使用的迭代器
   * @return
   */
  public Set<K> keySet(){
    Set<K> set = new HashSet<K>();
    for(int i=0;i<length;i++){
      Entry<K,V> e = table[i];
      while (e != null){
        set.add(e.key);
        e = e.next;
      }
    }
    return set;
  }

  public static void main(String[] args){
    MyHashMap map = new MyHashMap();
    map.put("1","a");
    map.put("2","b");
    map.put("3","c");
    map.put("4","d");
    map.put("2","bbbb");
    map.put("重地","bbbb");//hash值和"通话"相同，可用于验证hash碰撞
    map.put("通话","bbbb");//
    map.remove("通话");
    System.out.println("get--->"+map.get("2"));
    System.out.println(map.size());
    Iterator iterator1 = map.keySet().iterator();
    while (iterator1.hasNext()){
      System.out.println("==="+iterator1.next());
    }
  }

  /**
   * 自定义一个Entry对象存放KV，同时需要存在next来完成单个bucket下的链表形态
   */
  public class Entry<K,V>{
    private K key;
    private V value;
    public Entry<K,V> next;
    public Entry(K key,V value){
      this.key = key;
      this.value = value;
    }
  }
}
