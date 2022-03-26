package edu.zhengrui;

public class Main {
    public static void main(String[] args) {

    }

    private static Node<Integer> initializeTree(){
        Node<Integer> root = new Node<>();
        root.left = new Node<>(3);
        root.right = new Node<>(10);
        root.left.left = new Node<>(1);
        root.left.right = new Node<>(10);
        root.left = new Node<>(3);
        root.right = new Node<>(10);
    }

}
