package edu.zhengrui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //q1
        System.out.println(compressString("aabcccccaaa"));
        System.out.println(compressString("ab"));

        //q2
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands(grid));

        //q3
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};
//        Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//        Output: [[""]]
//        Output: [["a"]]
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagrams(strs2));
        System.out.println(groupAnagrams(strs3));
    }
    /*
    Question1: String Compression:
    Implement a method to perform basic string compression using the counts of repeated characters.
    For example, the string aabcccccaaa would become a2b1c5a3.
    If the "compressed" string would not become smaller than the original string, your method should
    return the original string.

    You can assume the string has only uppercase and lowercase letters (a - z).

    Input: "aabcccccaaa"
    Output: "a2b1c5a3"
    Input: "ab"
    Output: "ab"
     */
    public static String compressString(String str){
        //TODO: Write your code here
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;

        while (j < str.length()){
//            char ch = str.charAt(i);
            if (str.charAt(j) != str.charAt(i)){
                sb.append(str.charAt(i));
                sb.append(j - i);
                i = j;
            }
            j++;
        }
        sb.append(str.charAt(i));
        sb.append(j - i);

        return (sb.length() >= str.length()) ? str: sb.toString();
    }

    /*
    Question2: Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
    return the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1

    Input: grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    Output: 3
     */
    public static int numIslands(char[][] grid) {
        //TODO: Write your code here
        if (grid.length == 0){
            return 0;
        }

        int rows = grid.length, cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1'){
                    count++;
                    dfsCheckNeibors(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void dfsCheckNeibors(char[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';
        dfsCheckNeibors(grid, i - 1, j);
        dfsCheckNeibors(grid, i + 1, j);
        dfsCheckNeibors(grid, i, j - 1);
        dfsCheckNeibors(grid, i, j + 1);
    }

    /*
    Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically
    using all the original letters exactly once.

    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    Input: strs = [""]
    Output: [[""]]
    Input: strs = ["a"]
    Output: [["a"]]
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        //TODO: Write your code here
        if (strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String sorted = String.valueOf(arr);

            if (!map.containsKey(sorted)){
                map.put(sorted, new ArrayList<String>());
            }

            map.get(sorted).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //corner
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
}
