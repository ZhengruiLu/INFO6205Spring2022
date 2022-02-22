package edu.zhengrui;

import java.util.ArrayList;

public class Node<T> {
    public Node<T> left, right;
    public T data;
//    public ArrayList<Node<T>> children;

    public Node(T data){
        this.data = data;
    }
}
