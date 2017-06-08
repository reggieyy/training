package com.reggie.test.tree;

/**
 * Created by reggie on 2017/6/5.
 */
public class BinaryTree {

  private TreeNode root = null;

  public BinaryTree(){
    root = new TreeNode(1, "A");
  }

  public int height(){
    return height(root);
  }

  public int size(){
    return size(root);
  }

  /**
   *       A
   *    B     C
   *  D   E F   G
   * H I
   */
  public void createBainaryTree(){
    TreeNode newNodeB = new TreeNode(2,"B");
    TreeNode newNodeC = new TreeNode(3,"C");
    TreeNode newNodeD = new TreeNode(4,"D");
    TreeNode newNodeE = new TreeNode(5,"E");
    TreeNode newNodeF = new TreeNode(6,"F");
    TreeNode newNodeG = new TreeNode(7,"G");
    TreeNode newNodeH = new TreeNode(8,"H");
    TreeNode newNodeI = new TreeNode(9,"I");
    root.leftChild = newNodeB;
    root.rightChild = newNodeC;
    root.leftChild.leftChild = newNodeD;
    root.leftChild.rightChild = newNodeE;
    root.rightChild.leftChild = newNodeF;
    root.rightChild.rightChild = newNodeG;
    root.leftChild.leftChild.leftChild = newNodeH;
    root.leftChild.leftChild.rightChild = newNodeI;
  }

  /**
   * 获取树的高度（递归）
   * @param subTree
   * @return
   */
  public int height(TreeNode subTree){
    if(subTree == null){
      return 0;
    }else{
      int i = height(subTree.leftChild);
      int j = height(subTree.rightChild);
      return (i<j)?(j+1):(i+1);
    }
  }

  /**
   * 获取树的节点数（递归）
   * @param subTree
   * @return
   */
  public int size(TreeNode subTree){
   if(subTree == null){
     return 0;
   }else{
     return 1 + size(subTree.leftChild) + size(subTree.rightChild);
   }
  }

  /**
   * 前序遍历
   * @param subTree
   */
  public void preErgodic(TreeNode subTree){
    if(subTree == null) {
      return;
    }
    System.out.print(subTree.data+"->");
    preErgodic(subTree.leftChild);
    preErgodic(subTree.rightChild);
  }

  /**
   * 中序遍历
   * @param subTree
   */
  public void midErgodic(TreeNode subTree){
    if(subTree == null) {
      return;
    }
    midErgodic(subTree.leftChild);
    System.out.print(subTree.data+"->");
    midErgodic(subTree.rightChild);
  }

  /**
   * 后续遍历
   * @param subTree
   */
  public void afterErgodic(TreeNode subTree){
    if(subTree == null) {
      return;
    }
    afterErgodic(subTree.leftChild);
    afterErgodic(subTree.rightChild);
    System.out.print(subTree.data+"->");
  }

  /**
   * 翻转二叉树
   * @param subTree
   * @return
   *       A
   *    C     B
   *  G   F E   D
   *              H
   */
  public void turnTree(TreeNode subTree){
   if(subTree == null){
     return;
   }
   TreeNode left = subTree.leftChild;
   TreeNode right = subTree.rightChild;
   subTree.leftChild = right;
   subTree.rightChild = left;
   turnTree(subTree.leftChild);
   turnTree(subTree.rightChild);
  }

  /**
   * 判断节点值是否在树中
   * @param subTree
   * @param input
   * @return
   */
  public boolean isInside(TreeNode subTree,TreeNode input){
    if(subTree == null){
      return false;
    }
    if((subTree.data).equals(input.data)){
      return true;
    }else{
      return isInside(subTree.leftChild,input) || isInside(subTree.rightChild,input);
    }
  }

  /**
   * 构建二叉树的结构
   */
  private static class TreeNode{
    private String data;
    private int key;
    private TreeNode leftChild;
    private TreeNode rightChild;
//    private boolean isVisited;

    public TreeNode(int key,String data){
      this.data = data;
      this.key = key;
      this.leftChild = null;
      this.rightChild = null;
    }
  }

  /**
   * 测试工具
   * @param args
   */
  public static void main(String[] args){
    BinaryTree tree = new BinaryTree();
    tree.createBainaryTree();
    System.out.println("树的高度是："+tree.height());
    System.out.println("树的节点数是："+tree.size());
    System.out.print("前序遍历：");
    tree.preErgodic(tree.root);
    System.out.println();
    System.out.print("中序遍历：");
    tree.midErgodic(tree.root);
    System.out.println();
    System.out.print("后序遍历：");
    tree.afterErgodic(tree.root);
    System.out.println();
    System.out.println("翻转二叉树：");
    tree.turnTree(tree.root);
    System.out.print("前序遍历：");
    tree.preErgodic(tree.root);
    System.out.println("判断节点是否在二叉树中：");
    if(tree.isInside(tree.root,new TreeNode(10,"K"))){
      System.out.print("在树上");
    }else{
      System.out.print("不在树上");
    }
  }


}
