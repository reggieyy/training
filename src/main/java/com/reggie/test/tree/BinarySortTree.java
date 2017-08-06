package com.reggie.test.tree;

/**
 * 二叉排序树
 * Created by reggie on 2017/8/6.
 */
public class BinarySortTree<K,V> {

  public static void main(String[] args){
    int[] array = {1,2,5,10,28,39,7,3,14,25};
    BinarySortTree tree = new BinarySortTree(array);
    System.out.println("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println();
    System.out.println("当前树的节点个数为："+tree.size());
    System.out.println("新增节点开始...");
    tree.add(21,21,tree.root);
    tree.add(13,13,tree.root);
    tree.add(15,15,tree.root);
    tree.add(37,37,tree.root);
    System.out.println("新增节点结束...");
    System.out.println("当前树的节点个数为："+tree.size());
    System.out.println("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println();
    System.out.println("删除节点开始...");
//    tree.delete(37,tree.root,null,0);
    tree.delete(28,tree.root,null,0);
    System.out.println("删除节点结束...");
    System.out.println("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println("当前树的节点个数为："+tree.size());
  }

  private int size;
  private Node<K,V> root;
  private int[] global_array;

  /**
   * 删除节点
   * 删除节点的过程相对复杂
   * 1.如果是删除叶子节点或只有左子树或右子树，直接删除，指针连接前驱后继即可
   * 2.如果节点既有左子树又有右子树，则需要找到最近前驱或后继，然后从新对子树进行构造
   * @param key
   * @param node
   * @param prev 前驱
   */
  public void delete(int key,Node node,Node prev,int target){
    if(key < (int)node.key){
      delete(key,node.left,node,1);
    }else if(key > (int)node.key){
      delete(key,node.right,node,2);
    }else{
      if(node.left != null && node.right == null){
        prev.left = node.left;
        if(target == 1){
          prev.left = null;
        }else{
          prev.right = null;
        }
        size--;
      }else if(node.right != null && node.left == null){
        prev.right = node.right;
        if(target == 1){
          prev.left = null;
        }else{
          prev.right = null;
        }
        size--;
      }else if(node.left != null && node.right != null){
        /**
         * 为了方便理解，简单来说，删除这样的节点，其实就是调整连接该节点的三条指针，其中包含了一个前驱指向和两个由该节点出发
         * 的指向，在前驱模式下，左子树向左的深度都连接到右子树下左子树的末端。（前驱模式只考虑左子树，右子树不参加旋转，只重新
         * 被指针指向即可）
         */
        Node p = null,r = null,s = null;
        p = node;//首先将要删除的节点存放到临时元素p中
        node = node.left;
        while (node != null){
          if(node.right == null){
            r.right = null;
            s = node;
            break;
          }
          r = node;
          node = node.right;
        }
        if(target == 1){//前驱指向新的节点
          prev.left = s;
        }else{
          prev.right = s;
        }
        s.right = p.right;//新节点连接原有的右子树
        while (s != null){//找到左子树的左尽头，将该节点原有左子树接入到之后
          if(s.left == null){
            s.left = p.left;
            break;
          }
          s = s.left;
        }
        size--;
        s = null;
      }

    }
  }

  /**
   * 新增节点
   * 通过一级一级节点进行比较，如果小于当前节点key则作为左子树，如果大于则为右子树
   * @param key
   * @param value
   * @param node
   */
  public void add(int key,int value,Node node){
    if(key < (int)node.key){
      if(node.left == null){
        node.left = new Node(key,"value-"+value);
        size++;
      }else{
        add(key,value,node.left);
      }
    }
    if(key > (int)node.key){
      if(node.right == null){
        node.right = new Node(key,"value-"+value);
        size++;
      }else{
        add(key,value,node.right);
      }
    }
  }

  /**
   * 返回节点个数
   * @return
   */
  public int size(){
    return size;
  }

  /**
   * 中序遍历（二叉排序树中序遍历能够进行排序）
   * @param node
   */
  public void midErgodic(Node node){
    if(node == null){
      return;
    }
    midErgodic(node.left);
    System.out.print(node.key + " ");
    midErgodic(node.right);
  }

  public BinarySortTree(int[] array){
    if(array != null && array.length > 0){
      System.out.println("开始创建二叉排序树...");
      root = new Node(array[0],"value-"+array[0]);
      size++;
      global_array = array;
      this.buildTree(root);
      System.out.println("二叉排序树创建完毕...");
    }else{
      System.out.println("数组为空，无法创建二叉树");
    }
  }



  /**
   * 创建二叉排序树
   * 创建方法：
   * 1.循环数组，第一位作为根节点
   * 2.每次循环和第一位比较，如果小于他则放在左子树，如果大于则放在右子树
   * 3.子树的构造方式和第一个节点相同
   * @param node
   */
  private void buildTree(Node node){
    while (size < global_array.length){
      int array_key = global_array[size];
      if(array_key < (int)node.key){
        if(node.left == null){
          node.left = new Node(array_key,"value-"+array_key);
          size++;
          node = root;
        }else{
          buildTree(node.left);
        }
      }
      if(array_key > (int)node.key){
        if(node.right == null){
          node.right = new Node(array_key,"value-"+array_key);
          size++;
          node = root;
        }else{
          buildTree(node.right);
        }
      }
    }
  }


  /**
   * 数据结构
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
