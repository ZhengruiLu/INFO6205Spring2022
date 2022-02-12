package edu.zhengrui;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        //q1
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = addTwoNumber(l1, l2);
        res.printAll();

        //q2: how to print result like leetcode?
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
                this.map2.put(node, new Node(node.val));
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
    
    /*
    138. Copy List with Random Pointer
    solution3: iterative with O(1) space
    time: O(n), space: O(1)
     */
    public Node copyRandomList3(Node head){
        //corner case
        if (head == null){
            return null;
        }
        //link copy node after ori node
        Node ptr = head;
        while (ptr != null){
            Node copy = new Node(ptr.val);
            copy.next = ptr.next;
            ptr.next = copy;

            //move pointer to next ori node
            ptr = ptr.next.next;
        }

        //add random
        ptr = head;
        while (ptr != null){
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            //move poiner
            ptr = ptr.next.next;
        }

        //unlink all and relink again
        ptr = head;
        Node ptrNew = head.next;
        Node newHeadRecord = head.next;
        while (ptr != null){
            ptr.next = ptr.next.next;
            ptrNew.next = (ptrNew.next != null) ? ptrNew.next.next : null;

            //move pointers
            //move old list pointer
            ptr = ptr.next;
            //move new list pointer
            ptrNew = ptrNew.next;
        }

        return newHeadRecord;
    }



}
