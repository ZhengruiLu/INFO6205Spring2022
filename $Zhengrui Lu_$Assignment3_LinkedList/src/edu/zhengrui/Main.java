package edu.zhengrui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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

    /*
    23. Merge k Sorted Lists
    PriorityQueue
    time:O(logk * n * k), space: O(k)
    */
    public static ListNode mergeKLists(ListNode[] lists) {
        //use PriorityQueue to sort nodes' val from small to large
        Comparator<ListNode> compareVal = (a, b) -> Integer.compare(a.val, b.val);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(compareVal);

        //add each LinkedList head to pq
        for (ListNode node: lists){
            if (node != null){
                pq.offer(node);
            }
        }

        //keep pop the smallest one and link it to res
        ListNode head = new ListNode();
        ListNode tail = head;

        while (!pq.isEmpty()){
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = tail.next;

            //keep put next node into pq
            if (curr.next != null){
                pq.offer(curr.next);
            }
        }

        return head.next;
    }

    /**
     143. Reorder List
     time: O(n), space: O(1)
     */
    public void reorderList(ListNode head) {
        if (head == null){
            return;
        }

        //find mid node and divide ori into start to mid, mid + 1 to end
        ListNode mid = findMiddle(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;

        //reverse the second part
        l2 = reverseList(l2);//O(n/2)

        //merge l1 and reverse l2 in required order
        mergeList(l1, l2);//O(n)
    }

    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null){ //O(n/2)
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /*
    1 > 2 > 3 > null
    null<1<2<3
             p c n
    */
    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        ListNode nextTemp = new ListNode();

        while (curr != null){
            nextTemp = curr.next;

            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }

        return pre;
    }

    private void mergeList(ListNode l1, ListNode l2){
        ListNode p1 = l1, p2 = l2;

        while (l1 != null && l2 != null){
            p1 = l1.next;
            p2 = l2.next;

            l1.next = l2;
            l1 = p1;

            l2.next = l1;
            l2 = p2;
        }
    }

    /*
    234. Palindrome Linked List
    time: O(n), space: O(1)
    */
    public boolean isPalindrome(ListNode head) {
        //find middle and divide list into start to mid, mid + 1 to end
        ListNode l1 = head;
        ListNode mid = findMiddle(head);//O(n/2)
        ListNode l2 = mid.next;

        //reverse second part
        l2 = reverseList(l2);//O(n/2)

        //check l1 and reversed l2, if the first n or n-1 nodes' vals are same
        ListNode ptr2 = l2;
        boolean valIsSame = true;

        while (valIsSame && ptr2 != null){//O(n/2)
            if (l1.val != ptr2.val){
                valIsSame = false;
            }

            l1 = l1.next;
            ptr2 = ptr2.next;
        }

        //rebuild the ori list
        mid.next = reverseList(l2);//O(n/2)


        return valIsSame;
    }

}
