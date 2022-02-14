package edu.zhengrui;

import java.util.*;

public class Main {
    public static void main(String[] args){
        //q1
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = addTwoNumber(l1, l2);
        res.printList();

        //q2: how to print result like leetcode, including its random idx?

        //q3: Merge k Sorted Lists
        ListNode ele1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode ele2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode ele3 = new ListNode(2, new ListNode(6));
        ArrayList<ListNode> arr = new ArrayList<>();
        arr.add(ele1);
        arr.add(ele2);
        arr.add(ele3);
        mergeKLists(arr).printList();

//        q4: Reorder List
        ListNode l4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        reorderList(l4);
        l4.printList();

//        q5: Palindrome Linked List
        ListNode l5 = new ListNode(1, new ListNode(2, new ListNode(1)));
        boolean res5 = isPalindrome(l5);
        System.out.println(res5);

//        q6: Remove Nth Node From End of List
        ListNode l6 = new ListNode(1, new ListNode(2, new ListNode(3)));
        int n = 2;
        removeNthFromEnd(l6, 2).printList();

//        q7: Odd Even Linked List
        ListNode l7 = new ListNode(1, new ListNode(2, new ListNode(3)));
        oddEvenList(l7).printList();

//        q8: Insert into a Sorted Circular Linked List
        //how to print circular linked list?

//        q9: Next Greater Node In Linked List
        ListNode l9 = new ListNode(2, new ListNode(1, new ListNode(5)));
        int[] arr9 = nextLargerNodes(l9);
        System.out.println(Arrays.toString(arr9));

//        q10: Remove Duplicates from Sorted List II
        ListNode l10 = new ListNode(1, new ListNode(1, new ListNode(2)));
        deleteDuplicates(l10).printList();
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
    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
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
    public static void reorderList(ListNode head) {
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

    private static ListNode findMiddle(ListNode head){
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
    private static ListNode reverseList(ListNode head){
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

    private static void mergeList(ListNode l1, ListNode l2){
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
    public static boolean isPalindrome(ListNode head) {
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

    /*
    19. Remove Nth Node From End of List
    time: O(L), L refer to the length of list, space: O(1)
    */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode ptr = dummy;
        //move ptr n times, keep ptr and resPtr n range
        for (int i = 0; i <= n; i++){//O(n)
            ptr = ptr.next;
        }

        //move resPtr to correct position
        ListNode resPtr = dummy;
        while (ptr != null){//O(L - n)
            resPtr = resPtr.next;
            ptr = ptr.next;
        }

        resPtr.next = resPtr.next.next;

        return dummy.next;
    }

    /*
    328. Odd Even Linked List
    time: O(n), space: O(1)
    */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode oddPtr = dummy.next;
        ListNode evenStart = dummy.next.next;
        ListNode evenPtr = evenStart;

        //find odd part end, link odd idx ele
        while (evenPtr != null && evenPtr.next != null){//why use even and even.next as condition, instead of considering odd
            oddPtr.next = evenPtr.next;
            oddPtr = oddPtr.next;
            evenPtr.next = oddPtr.next;
            evenPtr = evenPtr.next;
        }

        //link odd part and even part
        oddPtr.next = evenStart;

        return dummy.next;
    }

    /*
    708. Insert into a Sorted Circular Linked List
    two pointers interation; do while
    time: O(n), space: O(1)
    */
    public static ListNode insert(ListNode head, int insertVal) {
        if (head == null){
            ListNode insertNode = new ListNode(insertVal, null);
            insertNode.next = insertNode;
            return insertNode;
        }

        ListNode pre = head, curr = head.next;
        Boolean insertMid = false;

        do{
            //case 1 pre.val <= insertVal <= curr.val
            if (pre.val <= insertVal && insertVal <= curr.val){
                insertMid = true;
            }
            //case 2 insertVal beyond the range of pre val and curr val
            if (pre.val > curr.val){
                if (insertVal >= pre.val || insertVal <= curr.val)
                    insertMid = true;
            }

            //if insertMid is ture, we insert middle position
            if (insertMid){
                pre.next = new ListNode(insertVal, curr);
                return head;
            }

            //move ptr
            pre = curr;
            curr = curr.next;
        }
        while (pre != head);

        //case 3 the list contains uniform values
        pre.next = new ListNode(insertVal, curr);
        return head;
    }

    /*
    1019. Next Greater Node In Linked List
    stack
    time: O(n), space: O(n)
    */
    public static int[] nextLargerNodes(ListNode head) {
        //use arraylist to store all vals
        List<Integer> vals = new ArrayList<>();//O(n)
        ListNode ptr = head;

        while (ptr != null){
            vals.add(ptr.val);
            ptr = ptr.next;
        }

        //use stack
        Stack<Integer> stack = new Stack<>();//O(n)
        int[] res = new int[vals.size()];

        for (int i = 0; i < vals.size(); i++){
            while (!stack.isEmpty() && vals.get(stack.peek()) < vals.get(i)){
                res[stack.pop()] = vals.get(i);
            }

            stack.push(i);
        }

        return res;
    }

    /*
    82. Remove Duplicates from Sorted List II
    sentinel
    Time: O(n), Space: O(1)
    */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        ListNode pre = sentinel;

        while (head != null){
            if (head.next != null && head.val == head.next.val){
                while (head.next != null && head.val == head.next.val){
                    head = head.next;
                }
                pre.next = head.next;
            }
            else{
                pre = pre.next;
            }
            head = head.next;
        }

        return sentinel.next;
    }

}
