package edu.zhengrui;

import java.util.*;

public class Main {
    /*
    437. Path Sum III
    prefix, recursion
    O(n), O(n)
    */
    public static void main(String[] args) {
        System.out.println(7/10);
    }
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode root, HashMap<Long, Integer> prefix, long curr, int targetSum){
        if (root == null){
            return 0;
        }

        int ans = 0;
        curr += root.val;

        ans = prefix.getOrDefault(curr - targetSum, 0);

        //record the prefix from root to curr node
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        //recursive left tree and right tree, to see if there exist ans > 0, if there is k == curr - targetSum in the map, it means from the p node to curr node, their sum is equal to targetSum
        ans += dfs(root.left, prefix, curr, targetSum);
        ans += dfs(root.right, prefix, curr, targetSum);
        //minus visited path
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ans;
    }

    /*
    236. Lowest Common Ancestor of a Binary Tree
    recursion
    O(n), O(1)
    */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }

        if (root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null){
            return root;
        }
        if (left != null){
            return left;
        }
        if (right != null){
            return right;
        }
        return null;
    }

    /*
    687. Longest Univalue Path
    getHeight, recursion
    O(n), O(h)
    */
    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return ans;
    }

    public int helper(TreeNode root){
        if (root == null){
            return 0;
        }

        int max = 0;
        int left = helper(root.left), right = helper(root.right);

        if (root.left != null && root.right != null && root.left.val == root.val && root.right.val == root.val){
            ans = Math.max(ans, left + right + 2);
        }

        if (root.left != null && root.val == root.left.val){
            max = left + 1;
        }
        if (root.right != null && root.val == root.right.val){
            max = Math.max(max, right + 1);
        }

        ans = Math.max(ans, max);
        return max;
    }

    /*
    297. Serialize and Deserialize Binary Tree
    recursion
    O(n), O(n)
    */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rSerialize(root, "");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));

        return rDeserialize(dataList);
    }

    private String rSerialize(TreeNode root, String s){
        if (root == null){
            s += "null,";
        }else{
            s += s.valueOf(root.val) + ",";
            s = rSerialize(root.left, s);
            s = rSerialize(root.right, s);
        }
        return s;
    }

    private TreeNode rDeserialize(List<String> dataList){
        if (dataList.get(0).equals("null")){
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rDeserialize(dataList);
        root.right = rDeserialize(dataList);

        return root;
    }

    /*
    987. Vertical Order Traversal of a Binary Tree
    use position recursion, sort by positions, add ans
    O(nlogn), O(n)
    */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();

        getPosition(root, 0, 0, nodes);
        Collections.sort(nodes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if (arr1[0] != arr2[0]){
                    return arr1[0] - arr2[0];
                }else if (arr1[1] != arr2[1]){
                    return arr1[1] - arr2[1];
                }else{
                    return arr1[2] - arr2[2];
                }
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        int size = 0;
        int lastCol = Integer.MIN_VALUE;
        for (int[] arr: nodes){
            int col = arr[0], row = arr[1], value = arr[2];
            if (col != lastCol){
                lastCol = col;
                ans.add(new ArrayList<Integer>());
                size++;
            }
            ans.get(size - 1).add(value);
        }

        return ans;
    }

    private void getPosition(TreeNode node, int col, int row, List<int[]> nodes){
        if (node == null){
            return;
        }
        nodes.add(new int[]{col, row, node.val});
        getPosition(node.left, col - 1, row + 1, nodes);
        getPosition(node.right, col + 1, row + 1, nodes);
    }

    /*
    222. Count Complete Tree Nodes
    use height recursion and complete tree nodes number
    time: O(logn * logn), space: O(n)
    */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }

        TreeNode left = root, right = root;
        int leftHeight = 0, rightHeight = 0;

        while (left != null){
            leftHeight++;
            left = left.left;
        }

        while (right != null){
            rightHeight++;
            right = right.right;
        }

        //if leftSubTree and rightSubTree's heights are equal, the total nodes number is 2^height - 1;
        if (leftHeight == rightHeight){
            return (int)Math.pow(2, leftHeight) - 1;
        }

        //count the max height of the tree
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /*
    129. Sum Root to Leaf Numbers
    recursion
    O(n), O(n)
    */
    public int sumNumbers(TreeNode root) {
        return preOrder(root, 0);
    }

    private int preOrder(TreeNode root, int prevSum){
        if (root == null){
            return 0;
        }

        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null){
            return sum;
        }else{
            return preOrder(root.left, sum) + preOrder(root.right, sum);
        }
    }

    /*
    1325. Delete Leaves With a Given Value
    recursion
    O(n), O(n)
    */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null){
            return root;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target){
            return null;
        }
        return root;
    }
}
