package com.reggie.test.conllection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by reggie on 2017/7/28.
 * 简单自定义HashMap
 */
public class MyHashMap<K,V> {

  private final int length = 31;
  private Entry<K,V>[] table;
  private Integer size;

  public MyHashMap(){
    size = 0;
    table = new Entry[length];
  }

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

  public Integer size(){
    return size;
  }

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
    map.put("重地","bbbb");
    map.put("通话","bbbb");
    map.remove("通话");
    System.out.println("get--->"+map.get("2"));
    System.out.println(map.size());
    Iterator iterator1 = map.keySet().iterator();
    while (iterator1.hasNext()){
      System.out.println("==="+iterator1.next());
    }
  }


  //自定义一个Entry对象存放KV，同时需要存在next来完成单个bucket下的链表形态
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
