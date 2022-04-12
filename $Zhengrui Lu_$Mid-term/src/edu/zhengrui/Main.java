package edu.zhengrui;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int ans = fibonacci(0, 1, 5);
        System.out.println(ans);


//        //Q1
//        int[] nums = {0,1,3,50,75};
//        int lower = 0, upper = 99;
//        List<String> ans1 = rangesList(nums, lower, upper);
//        System.out.println(ans1.toString());
//
//        //Q2
//        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//        ListNode ans = addTwoNumbers(l1, l2);
//        ans.printList();
//
//        //Q3: sorry, forgot how to print tree
//
//        //Q4
//        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
//        int[][] ans4 = mergeIntervals(intervals);
//        for (int[] interval: ans4){
//            System.out.print(Arrays.toString(interval));
//        }

    }

    private static int fibonacci(int first, int second, int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        else if (n == 3) {
            return first + second;
        }
        else {
            return fibonacci(second, first + second, n - 1);
        }
    }

    /*
    Q1
    0,1,3,50,75
        |
    low 1
    hi 99
    if (nums[i] != nums[i - 1] + 1){
        if (nums[i - 1] + 1 != nums[i] - 1)
            sout: a -> b
        else:
            sout: a
    }
    low = nums[i];
     */
    private static List<String> rangesList(int[] nums, int lower, int upper){
        if (nums.length == 1 && nums[0] == lower && nums[0] == upper){
            ArrayList<String> corner = new ArrayList<>();
            corner.add(String.valueOf(nums[0]));
            return corner;
        }

        List<String> ans = new ArrayList<>();
        //compare the first one with lower
        if (nums[0] > lower){
            if (nums[0] - 1 != lower){
                ans.add(String.valueOf(lower + 1) + "->" + (nums[0] - 1));
            }else{
                ans.add(String.valueOf(lower + 1));
            }
        }
        //compare the others
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1){
                if (nums[i - 1] + 1 != nums[i] - 1){
                    ans.add(String.valueOf(nums[i - 1] + 1) + "->" + (nums[i] - 1));
                }else{
                    ans.add(String.valueOf(nums[i - 1] + 1));
                }
            }

            lower = nums[i];
        }

        //compare the end with upper
        int endELe = nums[nums.length - 1];
        if (endELe < upper){
            if (upper != endELe + 1){
                ans.add(String.valueOf(endELe + 1) + "->" + (upper));
            }else{
                ans.add(String.valueOf(endELe + 1));
            }
        }

        return ans;
    }

    /*
    Q2
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        int carry = 0, sum = 0;
        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        while (p1 != null || p2 != null){
            int val1 = p1 != null ? p1.val : 0;
            int val2 = p2 != null ? p2.val : 0;

            sum = carry + val1 + val2;
            carry = sum / 10;
            pre.next = new ListNode(sum % 10);

            pre = pre.next;
            if (p1 != null){
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }

        if (carry > 0){
            pre.next = new ListNode(carry);
        }

        return dummy.next;
    }

    /*
    Q3
    inorder map
    k 9 3 15 20 7
    v 0 1 2   3 4

    preorder
    rootVal = preorder[preorderIdx++]
    new node(rootVal)

    root.left = recursion (preorder, left, map.get(rootVal) - 1)
    root.right = recursion get rootVal + 1 --- right

    return root
     */
    static HashMap<Integer, Integer> inorderMap;
    static int preorderIdx;
    private static TreeNode constructTree(int[] preorder, int[] inorder){
        inorderMap = new HashMap<>();
        preorderIdx = 0;
        //1.use map record inorder's order
        for (int i = 0; i < inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        //2.use preorder find the root first, the make sure the left subtree and right subtree
        return arrToTree(preorder, 0, preorder.length - 1);
    }

    private static TreeNode arrToTree(int[] preorder, int left, int right){
        if (left > right){
            return null;
        }

        int rootVal = preorder[preorderIdx++];
        TreeNode root = new TreeNode(rootVal);

        root.left = arrToTree(preorder, left, inorderMap.get(rootVal) - 1);
        root.right = arrToTree(preorder, inorderMap.get(rootVal) + 1, right);

        return root;
    }

    /*
    Q4
     */
    private static int[][] mergeIntervals(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        LinkedList<int[]> ans = new LinkedList<>();

        for (int[] interval: intervals){
            //non - overlapping
            if (ans.isEmpty() || ans.getLast()[1] < interval[0]){
                ans.add(interval);
            }else{
                ans.getLast()[1] = Math.max(ans.getLast()[1], interval[1]);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }

}


