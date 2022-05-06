package edu.zhengrui;

 public class TreeNode {
      int val;
      TreeNode left, right;

      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.right = right;
          this.left = left;
      }
 }
