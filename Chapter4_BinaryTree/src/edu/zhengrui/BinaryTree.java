package edu.zhengrui;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {
    public Node<T> root;

    public BinaryTree(){

    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node<T> root) {

    }
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node<T> root) {

    }
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node<T> root) {

    }

    public void levelOrder(){
        if (root != null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()){
            Node<T> node = queue.remove();
            if (node != null){
                System.out.println(node.data + ", ");
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }else{
                System.out.println();
                if (queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }
    public void levelOrderSameLine(){
        if (root != null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node<T> node = queue.remove();
            System.out.println(node.data + ", ");
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
    }
}
