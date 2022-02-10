package edu.zhengrui;

public class Node {
    int val;
    Node next;
    Node random;

    Node(int v){
        this.val = v;
        this.next = null;
        this.random = null;
    }

    public int getVal() {
        return val;
    }

}
