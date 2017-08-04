package com.reggie.test.tree;

import java.util.Scanner;

/**
 * 二叉树
 * Created by reggie on 2017/8/4.
 */
public class MyBinaryTree<K,V> {

  public static void main(String[] args){
    MyBinaryTree tree = new MyBinaryTree();
    tree.initBinaryTree();
    //前序遍历 ABDHIKEJCFG
    System.out.print("前序遍历：");
    tree.preErgodic(tree.root);
    System.out.println();
    //中序遍历 HDIKBJEAFCGG
    System.out.print("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println();
    System.out.print("后序遍历：");
    //后续遍历 HKIDJEBFGCA
    tree.afterErgodic(tree.root);
    System.out.println();
    System.out.println("这棵树的节点数为："+tree.size(tree.root));
    System.out.println("这棵树的高度："+tree.height(tree.root));
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入节点编码：");
    Object obj = tree.getNode(Integer.valueOf(sc.next()),tree.root);
    if(obj == null){
      System.out.println("节点不在树上");
    }else{
      System.out.println("节点在树上");
    }
  }

  private Node<K,V> root;

  /**
   * 计算树的节点个数 左子树节点数+右子树节点数+当前节点（1）
   * @param node
   * @return
   */
  public int size(Node node){
    if(node == null){
      return 0;
    }else{
      return size(node.left) + size(node.right) + 1;
    }
  }

  /**
   * 计算树的高度(以最高子树高度为准)
   * @param node
   * @return
   */
  public int height(Node node){
    if(node == null){
      return 0;
    }else{
      int i = height(node.left)+1;
      int j = height(node.right)+1;
      return (i<j)?j:i;
    }
  }

  /**
   * 抓取节点
   * @param key
   * @return
   */
  public Node getNode(K key,Node node){
    if(node == null){
      return null;
    }
    if(key == node.key){
      return node;
    }else{
      Node node1 = getNode(key,node.left);
      Node node2 = getNode(key,node.right);
      return node1 != null ? node1 : node2;
    }
  }

  /**
   *
   * 这不是一个完全二叉树，仅仅只是一个二叉树-_-
   *         A
   *      B       C
   *   D     E   F   G
   * H  I  J
   *     K
   *
   *
   */
  public void initBinaryTree(){
    root = new Node(1,"A");
    Node nodeB = new Node(2,"B");
    Node nodeC = new Node(3,"C");
    Node nodeD = new Node(4,"D");
    Node nodeE = new Node(5,"E");
    Node nodeF = new Node(6,"F");
    Node nodeG = new Node(7,"G");
    Node nodeH = new Node(8,"H");
    Node nodeI = new Node(9,"I");
    Node nodeJ = new Node(10,"J");
    Node nodeK = new Node(11,"K");
    root.left =  nodeB;
    root.right = nodeC;
    nodeB.left = nodeD;
    nodeB.right = nodeE;
    nodeC.left = nodeF;
    nodeC.right = nodeG;
    nodeD.left = nodeH;
    nodeD.right = nodeI;
    nodeE.left = nodeJ;
    nodeI.right = nodeK;
  }

  /**
   * 前序遍历
   * @param node
   */
  public void preErgodic(Node node){
    if(node == null){
      return;
    }
    System.out.print(node.value);
    preErgodic(node.left);
    preErgodic(node.right);
  }

  /**
   * 中序遍历
   * @param node
   */
  public void midErgodic(Node node){
    if(node == null){
      return;
    }
    midErgodic(node.left);
    System.out.print(node.value);
    midErgodic(node.right);
  }

  /**
   * 后序遍历
   * @param node
   */
  public void afterErgodic(Node node){
    if(node == null){
      return;
    }
    afterErgodic(node.left);
    afterErgodic(node.right);
    System.out.print(node.value);
  }

  /**
   * 数据结构，二叉树只有左右两个子树，所以构建时候除了本身还需要left和right两个指针域
   * @param <K>
   * @param <V>
   */
  public class Node<K,V> {
    private K key;
    private V value;
    private Node<K,V> left;
    private Node<K,V> right;

    public Node(K key,V value){
      this.key = key;
      this.value = value;
    }

  }

}
