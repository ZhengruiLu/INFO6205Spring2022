package edu.zhengrui;

import java.util.*;

public class Main {
    /*
    107. Binary Tree Level Order Traversal II
    time: O(n), space: O(n)
    */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();

        if (root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++){
                TreeNode currNode = queue.poll();
                list.add(currNode.val);

                if (currNode.left != null){
                    queue.offer(currNode.left);
                }
                if (currNode.right != null){
                    queue.offer(currNode.right);
                }
            }

            res.addFirst(list);
        }

        return res;
    }

    /*
    366. Find Leaves of Binary Tree
    recursion
    time: O(n), space: O(n)
    */
    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        int height = getHeight(root, list);

        return list;
    }

    public static int getHeight(TreeNode root, List<List<Integer>> list){
        if (root == null){
            return -1;
        }

        int leftHeight = getHeight(root.left, list);
        int rightHeight = getHeight(root.right, list);

        int currHeight = Math.max(leftHeight, rightHeight) + 1;

        if (list.size() == currHeight){
            list.add(new ArrayList<Integer>());
        }

        list.get(currHeight).add(root.val);

        return currHeight;
    }

    /*
    662. Maximum Width of Binary Tree
    dfs
    time: O(n), space: O(n)
    */
    private static int ans;

    public static int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        dfs(root, 0, 0, map);
        return ans;
    }

    public static void dfs(TreeNode root, int depth, int pos, Map<Integer, Integer> map){
        if (root == null){
            return;
        }

        map.computeIfAbsent(depth, val -> pos);
        ans = Math.max(ans, pos - map.get(depth) + 1);

        dfs(root.left, depth + 1, pos * 2, map);
        dfs(root.right, depth + 1, pos * 2 + 1, map);
    }

    /*
    515. Find Largest Value in Each Tree Row
    bfs
    time: O(n), space: O(n)
    */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()){
            int size = que.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                if (curr != null){
                    max = Math.max(max, curr.val);
                }

                if (curr.left != null){
                    que.offer(curr.left);
                }
                if (curr.right != null){
                    que.offer(curr.right);
                }
            }
            list.add(max);
        }

        return list;
    }
}


