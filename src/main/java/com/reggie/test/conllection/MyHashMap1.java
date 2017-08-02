package com.reggie.test.conllection;

import com.reggie.test.conllection.MyHashMap.Entry;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by reggie on 2017/8/2.
 */
public class MyHashMap1<K,V> {

  private Entry<K,V>[] table;
  private int size;
  private int length = 31;

  public MyHashMap1(){
    size = 0;
    table = new Entry[length];
  }

  /**
   *
   * @param key
   * @param value
   */
  public void put(K key,V value){
    size++;
    Entry<K,V> e = new Entry<K, V>(key,value);
    int i = key.hashCode()%length;
    if(table[i] == null){
      table[i] = e;
      return;
    }
    Entry<K,V> ex = table[i];
    while(ex != null){
      if(ex.key == key){
        ex.value = value;
        size--;
        return;
      }
      if(ex.next == null){
        ex.next = e;
        return;
      }
      ex = ex.next;
    }

  }

  public V get(K key){
    int i = key.hashCode()%length;
    Entry<K,V> e = table[i];
    while (e != null){
      if(e.key == key){
        return e.value;
      }
    }
    return null;
  }

  public void remove(K key){
    int i = key.hashCode()%length;
    Entry<K,V> prev = null;
    Entry<K,V> e = table[i];
    while (e != null){
      if(e.key == key){
        if(prev == null){
          table[i] = e.next;
        }else{
          prev.next = e.next;
        }
      }
      prev = e;
      e = e.next;
    }
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

  public int size(){
    return size;
  }

  /**
   * Entry对象，包含了key，value，next指针（存放下一个元素，保证链式结构）
   * @param <K>
   * @param <V>
   */
  public class Entry<K,V>{

    private K key;
    private V value;

    private Entry<K,V> next;
    public Entry(K key,V value){
      this.key = key;
      this.value = value;
    }

  }


  public static void main(String[] args){
    MyHashMap1 map = new MyHashMap1();
    map.put("1","a");
    map.put("2","b");
    map.put("3","c");
    map.put("4","d");
    System.out.println("get->"+map.get("1"));
    map.remove("3");
    Iterator it = map.keySet().iterator();
    while (it.hasNext()){
      Object key = it.next();
      System.out.println(key+"--"+map.get(key));
    }
  }

}
