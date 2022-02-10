package edu.zhengrui;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        //q1
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = addTwoNumber(l1, l2);
        res.printAll();
    }

    //helpers

    /*
    2. Add Two Numbers
    time: O(max(m, n)), space: O(max(m, n))
     */
    private static ListNode addTwoNumber(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        ListNode p = l1, q = l2;
        int sum = 0, carry = 0;

        while (p != null && q != null){
            int l1Val = (p != null) ? p.val : 0;
            int l2Val = (q != null) ? q.val : 0;

            sum = carry + l1Val + l2Val;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null){
                p = p.next;
            }
            if (q != null){
                q = q.next;
            }
        }

        if (carry > 0){
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }

    /*
    138. Copy List with Random Pointer
    solution1: recursive
    time: O(n), space: O(n)
     */
    HashMap<Node, Node> map = new HashMap<>();
    private Node copyRandomList(Node head){
        if (head == null){
            return null;
        }

        if (this.map.containsKey(head)){
            return this.map.get(head);
        }

        Node copy = new Node(head.val);
        this.map.put(head, copy);

        copy.next = this.copyRandomList(head.next);
        copy.random = this.copyRandomList(head.random);

        return copy;
    }

    /*
    138. Copy List with Random Pointer
    solution2: iterative
    time: O(n), space: O(n)
     */
    HashMap<Node, Node> map2 = new HashMap<>();

    public Node copyNode(Node node){
        if (node != null){
            if (this.map2.containsKey(node)){
                return this.map2.get(node);
            }else{
                this.map2.put(node, new Node(node.val, null, null));
                return this.map2.get(node);
            }

        }

        return null;
    }

    public Node copyRandomList2(Node head) {
        if (head == null){
            return null;
        }

        Node old = head;
        Node newNode = new Node(old.val);
        this.map2.put(old, newNode);

        while (old != null){

            newNode.random = copyNode(old.random);
            newNode.next = copyNode(old.next);

            old = old.next;
            newNode = newNode.next;
        }

        return this.map2.get(head);

    }
    


}
