package edu.zhengrui;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;

        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }

        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }

    public static int minDeletions(String s) {
        //count freq
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
        }

        //sort by freq decreasingly
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < freq.length; i++){
            if (freq[i] > 0){
                pq.add(freq[i]);
            }
        }

        int deleteCount = 0;
        while (pq.size() > 1){
            int topEle = pq.remove();

            //if have the same freq
            if (topEle == pq.peek()){
                //decrement, each time by 1
                if (topEle - 1 > 0){
                    pq.add(topEle - 1);
                }
                deleteCount++;
            }
        }

        return deleteCount;
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 1; i < prefixSum.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        HashMap<Integer, Integer> mod = new HashMap<>();

        for (int i = 0; i < prefixSum.length; i++){
            int sumMod = prefixSum[i] % k;
            if (mod.containsKey(sumMod) && i > mod.get(sumMod) + 1){//make sure they are continous
                return true;
            }
            else if (!mod.containsKey(sumMod)){
                mod.put(sumMod, i);
            }
        }

        return false;
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();

        if (digits.length() == 0) {
            return combinations;
        }
        //use map record every number's letters
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        //use backtrack pattern
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        }
        else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();

            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();

        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);

            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());

            ans.get(key).add(s);
        }

        return new ArrayList(ans.values());
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1, -1};
        }

        int[] ans = new int[2];

        ans[0] = findIdx(nums, target, true);

        if (ans[0] == -1){
            return new int[]{-1, -1};
        }

        ans[1] = findIdx(nums, target, false);

        return ans;
    }

    private int findIdx(int[] nums, int target, boolean isFirst){
        int left = 0, right = nums.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (nums[mid] == target){
                if (isFirst){
                    if (mid == left || nums[mid - 1] != target){
                        return mid;
                    }else{
                        right--;
                    }
                }else{
                    if (mid == right || nums[mid + 1] != target){
                        return mid;
                    }else{
                        left++;
                    }
                }
            }else if (nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return -1;
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start + 1 < end){
            int mid = start + (end - start) / 2;

            if (nums[start] < nums[mid]){
                //if in the sorted part, must add equal ==, or it may miss the correct answer
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }else{
                    start = mid;
                }
            }else{
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }

        if (nums[start] == target){
            return start;
        }
        if (nums[end] == target){
            return end;
        }

        return -1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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


