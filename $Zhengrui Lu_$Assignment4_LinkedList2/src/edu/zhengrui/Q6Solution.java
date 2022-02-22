package edu.zhengrui;

import java.util.Random;

class Q6Solution {
    ListNode head = new ListNode();
    int len = 0;

    public Q6Solution(ListNode head) {
        this.head = head;

        ListNode ptr = head;
        while (ptr != null){
            len++;
            ptr = ptr.next;
        }

    }

    public int getRandom() {
        ListNode dummy = new ListNode(0, head);
        ListNode ptr = dummy.next;

        Random random = new Random();
        int randomStep = random.nextInt(len);

        for (int i = 0; i < randomStep; i++){
            ptr = ptr.next;
        }

        return ptr.val;
    }
}