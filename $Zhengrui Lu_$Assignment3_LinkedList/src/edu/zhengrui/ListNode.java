package edu.zhengrui;

public class ListNode {
    int val;
    ListNode next;

    ListNode(){};
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }

    public void printAll(){
        System.out.print(this.val);
        if (this.next != null){
            this.next.printAll();
        }
    }
}
