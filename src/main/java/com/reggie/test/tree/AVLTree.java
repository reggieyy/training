package com.reggie.test.tree;

/**
 * 平衡二叉树
 * （平衡二叉树是基于二叉排序树，所以在元素分布上按照大小值分别挂载到不同的子树上）
 * Created by reggie on 2017/8/7.
 */
public class AVLTree {

  public static void main(String[] args){
    AVLTree tree = new AVLTree();
    System.out.println("AVL树测试类");
    tree.buildTree(tree.root);
    System.out.print("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println();
    System.out.print("前序遍历：");
    tree.preErgodic(tree.root);

    AVLTree tree1 = new AVLTree();
    Node newRoot = tree1.root;
    for(int i=1;i<tree1.array.length;i++){
      Node node = tree1.insert(newRoot,tree.array[i]);
      if(node.isRoot){
        newRoot = node;
      }
    }
    System.out.println();
    System.out.print("中序遍历：");
    tree1.midErgodic(newRoot);
    System.out.println();
    System.out.print("前序遍历：");
    tree1.preErgodic(newRoot);

  }

  private int size;
  private int[] array = {5,10,15,3,2,14};
  private Node root;

  /**
   * 初始化
   */
  public AVLTree(){
    size = 0;//初始化节点数
    root = new Node(array[0],null,null,null);//优先创建root节点
    root.isRoot = true;
    size++;
//    System.out.println("创建二叉树开始...");
//    buildTree(root);
//    System.out.println("创建二叉树结束...");
  }


  /**
   * 平衡二叉树插入节点
   * @param key
   */
  public Node insert(Node node,int key){
    if(node == null){
      node = new Node(key,null,null,null);
    }else{
      if(key < node.key){
        node.left = insert(node.left,key);
        if(Math.abs(bf(node)) > 1){
          if(key < node.left.key){
            node = llRotate(node);
          }else{
            node = lrRotate(node);
          }
        }
      }
      if(key > node.key){
        node.right = insert(node.right,key);
        if(Math.abs(bf(node)) > 1){
          if(key > node.right.key){
            node = rrRotate(node);
          }else{
            node = rlRotate(node);
          }
        }
      }
    }
    return node;
  }

  /**
   * 右旋转
   * @param node
   * @return
   */
  public Node llRotate(Node node){
    Node newNode;


    newNode = node.left;
    if(node.isRoot){
      newNode.isRoot = true;
    }
    node.left = newNode.right;
    newNode.right = node;

    return newNode;
  }

  /**
   *
   * 左旋转（和右旋转的指针完全相反）
   * @param node
   * @return
   */
  public Node rrRotate(Node node){
    Node newNode;
    newNode = node.right;
    if(node.isRoot){
      newNode.isRoot = true;
    }
    node.right = newNode.left;
    newNode.left = node;

    return newNode;
  }

  /**
   * 先右旋再左旋
   * 右旋交换值顺序，左旋平衡
   * @param node
   * @return
   */
  public Node lrRotate(Node node){
    node.left = rrRotate(node.left);
    return llRotate(node);
  }

  public Node rlRotate(Node node){
    node.right = llRotate(node.right);
    return rrRotate(node);
  }



  /**
   * 创建平衡二叉树
   * @param node
   *
   */
  public void buildTree(Node node){
    while (size < array.length){
      int pos = size;
      if(array[pos] < node.key){//小于插入左子树
        if(node.left == null){
          node.left = new Node(array[pos],null,null,null);
          size++;
          node = root;
        }else{
          buildTree(node.left);
        }
      }
      if(array[pos] > node.key){//大于写入右子树
        if(node.right == null){
          node.right = new Node(array[pos],null,null,null);
          size++;
          node = root;
        }else{
          buildTree(node.right);
        }
      }
    }
  }

  /**
   * 前序遍历
   * @param node
   */
  public void preErgodic(Node node){
    if(node == null){
      return;
    }else{
      System.out.print(node.key+",");
      preErgodic(node.left);
      preErgodic(node.right);
    }
  }

  /**
   * 中序遍历
   * @param node
   */
  public void midErgodic(Node node){
    if(node == null){
      return;
    }else{
      midErgodic(node.left);
      System.out.print(node.key+",");
      midErgodic(node.right);
    }
  }

  /**
   * 后序遍历
   * @param node
   */
  public void afterErgodic(Node node){
    if(node == null){
      return;
    }else{
      afterErgodic(node.left);
      afterErgodic(node.right);
      System.out.print(node.key+",");
    }
  }

  /**
   * 计算树的高度
   * @param node
   * @return
   */
  public int height(Node node){
    if(node == null){
      return 0;
    }else{
      int i = height(node.left);
      int j = height(node.right);
      return i>j ? i+1 : j+1;
    }
  }

  /**
   * 计算平衡因子
   * @param node
   * @return
   */
  public int bf(Node node){
    if(node == null){
      return 0;
    }else{
      int i = height(node.left);
      int j = height(node.right);
      return i-j;
    }
  }


  /**
   * 数据结构
   * 属性域（key，value）
   * 指针域（left，right）
   */
  public class Node{

    private int key;
    private Object value;
    private Node left;
    private Node right;
    private int bf;//balance factor
    private boolean isRoot = false;

    public Node(int key,Object value,Node left,Node right){
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
    }

  }

}
