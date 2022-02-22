package edu.zhengrui;

public class LinkList {
    ListNode head;
    ListNode tail;
    int count;

    public LinkList(){
        head = null;
        tail = null;
        count = 0;
    }

    public void add(ListNode node, boolean addToHead){
        count++;

        if (head == null){
            head = node;
            tail = node;
            return;
        }

        if (addToHead){
            node.next = head;
            head = node;
        }else{
            tail.next = node;
            tail = node;
        }
    }

    public void printList(){
        if (head == null){
            System.out.println("[]");
        }

        ListNode temp = head;
        System.out.println("[ ");
        while (temp != null){
            System.out.println(temp.val + "->");
            temp = temp.next;
        }

        System.out.println("Null ]");
    }
}
