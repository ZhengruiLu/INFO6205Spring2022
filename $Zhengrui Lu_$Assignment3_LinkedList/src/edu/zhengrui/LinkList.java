package edu.zhengrui;

import sun.awt.image.ImageWatched;

public class LinkList {
    public ListNode head;
    public ListNode tail;
    public int count;

    public LinkList() {
        head = null;
        tail = null;
        count = 0;
    }

    public void printList(){
        if(head == null){
            System.out.println("[]");
        }

        ListNode temp = head;

        System.out.print("[ ");
        while(temp != null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("Null ]");

    }

}
