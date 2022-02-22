package edu.zhengrui;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args){
        //q1: 1669. Merge In Between Linked Lists
        ListNode one = new ListNode(0);
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(2);
        ListNode list1Second = new ListNode(3, new ListNode(4, new ListNode(5)));
        one.next = two;
        two.next = three;
        three.next = list1Second;
        ListNode list2 = new ListNode(1000000, new ListNode(1000001, new ListNode(1000002)));
        int a = 3, b = 4;
        printQuestionForQ1(one, list2, a, b);
        printResForQ1(mergeInBetween(one, a, b, list2));

        //q2: 86. Partition List
        ListNode q2node = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        int x = 3;
        printQuestionForQ2(q2node, x);
        printResForQ2(partition(q2node, x));

        //q3: 2074. Reverse Nodes in Even Length Groups
        ListNode q3node = new ListNode(5, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(9, new ListNode(1, new ListNode(7, new ListNode(3, new ListNode(8, new ListNode(4))))))))));
        printQuestionForQ3(q3node);
        ListNode q3res = reverseEvenLengthGroups(q3node);
        printResForQ3(q3res);

        //q4: 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
        //5,3,1,2,5,1,2
        ListNode q4node = new ListNode(5, new ListNode(3, new ListNode(1, new ListNode(2, new ListNode(5, new ListNode(1, new ListNode(2)))))));
        int[] res4 = nodesBetweenCriticalPoints(q4node);
        printResForQ4(q4node);

        //q5: 148. Sort List
        ListNode q5node = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        printQuestionForQ5(q5node);
        printResForQ5(sortList(q5node));

        //q6: 382. Linked List Random Node
        ListNode q6node = new ListNode(1, new ListNode(2, new ListNode(3)));
        Q6Solution obj = new Q6Solution(q6node);

        printQuestionForQ6(q5node);
        printResForQ6(obj.getRandom());

        //q7
        ListNode q7node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printQuestionForQ7(q7node);
        printResForQ7(reverseBetween(q7node, 2, 4));

        //q8
        ListNode q8node = new ListNode(1, new ListNode(2, new ListNode(3)));
        printQuestionForQ8(q8node);
        printResForQ8(splitListToParts(q8node, 5));

        //q9
        ListNode q9node = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3))));
        int[] nums = {0,1,3};
        printQuestionForQ9(q9node, nums);
        printResForQ9Q10(numComponents(q9node, nums));

        //q10
        ListNode q10node = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
        printQuestionForQ10(q10node);
        printResForQ9Q10(pairSum(q10node));
    }

    //helpers
    public static void printList(ListNode head){
        if (head == null){
            System.out.println("[]");
        }

        ListNode temp = head;
        System.out.print("[ ");
        while (temp != null){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }

        System.out.println("Null ]");
    }

    public static void printQuestionForQ1(ListNode l1, ListNode l2, int a, int b){
        System.out.println("q1");
        System.out.println("Before merge: ");
        System.out.print("l1: ");
        printList(l1);
        System.out.print("l2: ");
        printList(l2);
        System.out.println("a = " + a + ", b = " + b);
    }

    public static void printResForQ1(ListNode res1){
        System.out.println("After merge: ");
        printList(res1);
        System.out.println();
    }

    public static void printQuestionForQ2(ListNode l1, int x){
        System.out.println("q2");
        System.out.println("Before partition: ");
        printList(l1);
        System.out.println("x = " + x);
    }

    public static void printResForQ2(ListNode res){
        System.out.println("After partition: ");
        printList(res);
        System.out.println();
    }
    public static void printQuestionForQ3(ListNode l3){
        System.out.println("q3");
        System.out.println("Before reverse Nodes in even length groups: ");
        printList(l3);
    }

    public static void printResForQ3(ListNode res){
        System.out.println("After reverse: ");
        printList(res);
        System.out.println();
    }

    public static void printResForQ4(ListNode l4){
        System.out.println("q4");
        System.out.println("Before: ");
        printList(l4);
        System.out.println("Result: ");
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(l4)));
        System.out.println();
    }

    public static void printQuestionForQ5(ListNode l5){
        System.out.println("q5");
        System.out.println("Before sort: ");
        printList(l5);
    }

    public static void printResForQ5(ListNode res){
        System.out.println("After sort: ");
        printList(res);
        System.out.println();
    }

    public static void printQuestionForQ6(ListNode l6){
        System.out.println("q6");
        System.out.println("Solution: ");
        printList(l6);
    }

    public static void printResForQ6(int n){
        System.out.println("getRandom: ");
        System.out.println(n);
    }

    public static void printQuestionForQ7(ListNode l7){
        System.out.println("q7");
        System.out.println("Before reverse: ");
        printList(l7);
        System.out.println();
    }

    public static void printResForQ7(ListNode res){
        System.out.println("After reverse: ");
        printList(res);
        System.out.println();
    }

    public static void printQuestionForQ8(ListNode l8){
        System.out.println("q8");
        System.out.println("Before splitting: ");
        printList(l8);
        System.out.println();
    }

    public static void printResForQ8(ListNode[] res){
        System.out.println("After splitting: ");
        System.out.print("[");
        for (ListNode node: res) {
            printList(node);
        }
        System.out.print("]");
        System.out.println();
        System.out.println();
    }

    public static void printQuestionForQ9(ListNode head, int[] nums){
        System.out.println("q9");
        System.out.println("LinkedList: ");
        printList(head);
        System.out.println("nums: " + Arrays.toString(nums));
    }

    public static void printResForQ9Q10(int res){
        System.out.println("res: " + res);
        System.out.println();
    }

    public static void printQuestionForQ10(ListNode head){
        System.out.println("q10");
        System.out.println("LinkedList: ");
        printList(head);
    }
    /*
    1669. Merge In Between Linked Lists
    pointer
    time: O(m + n), m refers to list1's length, n refers to list2's length
    space: O(1)

    question:
    this solution can pass in leetcode with the pre constraints, but it can't pass in IDE, shows that
    "NullPointerException" in for loop when do "if (afterBNode.val == a - 1){}"
    */
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preANode = null;
        ListNode afterBNode = list1;

        for (int i = 0; i < b; i++){//O(b)
            if (afterBNode.val == a - 1){
                preANode = afterBNode;
            }
            afterBNode = afterBNode.next;
        }

        preANode.next = list2;

        while (list2.next != null){//O(m), m refers to the len of list2
            list2 = list2.next;
        }

        list2.next = afterBNode.next;
        afterBNode.next = null;

        return list1;
    }

    /*
    86. Partition List
    two pointer
    time: O(n), space: O(1)
    */
    public static ListNode partition(ListNode head, int x) {
        ListNode beforeDummy = new ListNode();
        ListNode beforePtr = beforeDummy;
        ListNode afterDummy = new ListNode();
        ListNode afterPtr = afterDummy;

        while (head != null){
            if (head.val < x){
                beforePtr.next = head;
                beforePtr = beforePtr.next;
            }
            else{
                afterPtr.next = head;
                afterPtr = afterPtr.next;
            }
            head = head.next;
        }

        afterPtr.next = null;
        beforePtr.next = afterDummy.next;

        return beforeDummy.next;
    }

    /*
    2074. Reverse Nodes in Even Length Groups
    pointer, check even and odd idx, iterative
    time: O(n), space: O(1)
    */
    public static ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = head;
        int count = 0;

        while (cur != null){
            //get length of current group
            count++;
            ListNode tryLen = cur;
            int len = 0;

            while (len < count && tryLen != null){

                len++;
                tryLen = tryLen.next;
            }

            //check the len is odd or even
            if (len % 2 == 1){
                for (int i = 0; i < len; i++){
                    pre = pre.next;
                    cur = cur.next;
                }
            }
            else{
                for (int i = 0; i < len - 1; i++){
                    ListNode removed = cur.next;
                    cur.next = cur.next.next;
                    removed.next = pre.next;
                    pre.next = removed;
                }
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    /*
    2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
    pre-cur-next pointers, min, max
    time: O(n), space: O(1)
    */
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode pre = head, cur = head.next;
        int firstIdx = 0, curIdx = 1, preIdx = 0;
        int minDiff = Integer.MAX_VALUE, maxDiff = -1;

        while (cur.next != null){
            ListNode next = cur.next;

            boolean isMinima = cur.val < pre.val && cur.val < next.val;
            boolean isMaxima = cur.val > pre.val && cur.val > next.val;

            if (isMinima || isMaxima){
                if (firstIdx == 0){
                    firstIdx = curIdx;
                }else{
                    maxDiff = curIdx - firstIdx;
                    minDiff = Math.min(minDiff, curIdx - preIdx);
                }
                preIdx = curIdx;
            }
            curIdx++;
            pre = cur;
            cur = cur.next;
        }

        return maxDiff == -1 ? new int[]{-1, -1} : new int[]{minDiff, maxDiff};
    }

    public static ListNode sortList(ListNode head) {
        //corner case
        if (head == null){
            return null;
        }

        //1.get len of ori list
        int totalLen = getLength(head);
        ListNode dummy = new ListNode(0, head);

        //2.merge sort: divide and merge
        for (int subLen = 1; subLen < totalLen; subLen <<= 1){
            ListNode pre = dummy, curr = dummy.next;

            while (curr != null){
                ListNode head1 = curr;
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++){
                    curr = curr.next;
                }

                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++){
                    curr = curr.next;
                }

                ListNode next = null;
                if (curr != null){
                    next = curr.next;
                    curr.next = null;
                }

                ListNode merge = mergeList(head1, head2);
                pre.next = merge;

                while (pre.next != null){
                    pre = pre.next;
                }
                curr = next;
            }
        }

        return dummy.next;
    }

    public static int getLength(ListNode head){
        ListNode ptr = head;
        int len = 0;

        while (ptr != null){
            len++;
            ptr = ptr.next;
        }

        return len;
    }

    public static ListNode mergeList(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (head1 != null && head2 != null){
            if (head1.val < head2.val){
                curr.next = head1;
                head1 = head1.next;
            }
            else{
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        if (head1 != null){
            curr.next = head1;
        }
        if (head2 != null){
            curr.next = head2;
        }

        return dummy.next;
    }

    /*
    92. Reverse Linked List II
    two pointers
    time: O(n), space: O(1)
    */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++){
            pre = pre.next;
        }

        ListNode curr = pre.next, next;
        for (int i = 0; i < right - left; i++){
            next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    /*
    725. Split Linked List in Parts
    two pointers
    time: O(n), space: O(1)
    */
    public static ListNode[] splitListToParts(ListNode head, int k) {
        int len = getLen(head);//O(n)
        int width = len / k, firstParts = len % k;
        ListNode[] ans = new ListNode[k];
        ListNode curr = head;

        for (int i = 0; i < k; i++){//O(k)
            //remember the head of splited list
            ListNode root = curr;
            for (int j = 0; j < width + (i < firstParts ? 1:0) - 1; j++){//O(n / k)
                if (curr != null){
                    curr = curr.next;
                }
            }

            if (curr != null){
                //record the curr end node
                ListNode pre = curr;
                //move the curr pointer
                curr = curr.next;
                //let the node next end with null
                pre.next = null;
            }
            ans[i] = root;
        }

        return ans;
    }

    public static int getLen(ListNode head){
        int len = 0;
        ListNode ptr = head;
        while (ptr != null){
            len++;
            ptr = ptr.next;
        }
        return len;
    }

    /*
    817. Linked List Components
    n refers to the len of linklist, m refers to the len of nums
    time: O(n + m), space: O(m)
    */
    public static int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for (int num: nums){//O(m)
            set.add(num);
        }

        ListNode curr = head;
        while (curr != null){//O(n)
            if ((set.contains(curr.val) && curr.next == null) || (set.contains(curr.val) && !set.contains(curr.next.val))){
                count++;
            }
            curr = curr.next;
        }

        return count;
    }

    /*
    2130. Maximum Twin Sum of a Linked List
    time: O(n), space: O(1)
    */
    public static int pairSum(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast.next.next != null){//O(n/2)
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        //make sure the first half list
        slow.next = null;

        //reverse the second half
        ListNode curr = second;
        ListNode pre = null;

        while (curr != null){//O(n/2)
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        int max = Integer.MIN_VALUE;
        while (head != null){//O(n/2)
            int sum = head.val + pre.val;
            max = Math.max(max, sum);
            head = head.next;
            pre = pre.next;
        }

        return max;
    }
}
