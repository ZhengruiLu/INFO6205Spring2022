/*
1.Sort Colors (https://leetcode.com/problems/sort-colors/ (Links to an external site.))
2.Majority Element II (https://leetcode.com/problems/majority-element-ii/ (Links to an external site.))
3.H-Index (https://leetcode.com/problems/h-index/ (Links to an external site.))
4.Intersection of Two Arrays (https://leetcode.com/problems/intersection-of-two-arrays/ (Links to an external site.))
5.Find K Closest Elements (https://leetcode.com/problems/find-k-closest-elements/ (Links to an external site.))
6.Reorganize String (https://leetcode.com/problems/reorganize-string/ (Links to an external site.))
7.Custom Sort String (https://leetcode.com/problems/custom-sort-string/ (Links to an external site.))
8.Pancake Sorting (https://leetcode.com/problems/pancake-sorting/ (Links to an external site.))
9.Sort Array by Increasing Frequency (https://leetcode.com/problems/sort-array-by-increasing-frequency/ (Links to an external site.))
10.Top K Frequent Words (https://leetcode.com/problems/top-k-frequent-words/ (Links to an external site.))

 */

import java.util.*;

public class Main {
    public static void main(String[] args){
        //q1
        int[] nums = {2,0,2,1,1,0}; //Output: [0,0,1,1,2,2]
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        //q2
        int[] nums2 = {3,2,3};
        List<Integer> list = majorityElement2(nums2);
        System.out.println(Arrays.toString(list.toArray()));
        //q3
        int[] citations = {3,0,6,1,5};
        System.out.println(hIndex(citations));
        //q4
        int[] test1 = {4,9,5};
        int[] test2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersection(test1,test2)));
        //q5
        int[] arr = {1,2,3,4,5};
        int k = 4, x = 3;
        System.out.println(findClosestElements(arr, k, x).toString());
        //q6
        String s = "aab", s1 = "aaab";
        System.out.println(reorganizeString(s)); //"aba"
        System.out.println(reorganizeString(s1));//""
        //q7
        String order = "cba", str = "abcd";
        System.out.println(customSortString(order, str));//Output: "cbad"
        String order2 = "cbafg", str2 = "abcd";
        System.out.println(customSortString(order2, str2));//Output: "cbad"
        //q8
        int[] pancakeArr = {3,2,4,1};
        System.out.println(pancakeSort(pancakeArr).toString());
        //q9
        int[] nums3 = {1,1,2,2,2,3};
        System.out.println(Arrays.toString(frequencySort(nums3)));
        //q10
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k2 = 4;
        System.out.println(topKFrequent(words, k2).toString());

    }

    private static void swap(int[] nums, int i, int j){
        if (i < 0 || j < 0 || i > nums.length || j > nums.length || i == j || nums.length == 0){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //Sort Colors
    //time O(n)
    //space O(1)
    public static void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        int p = 0;

        while (p <= p2){
            if (nums[p] < 1){
                swap(nums, p, p0);
                p0++;
                p++;
            }
            else if (nums[p] == 1){
                p++;

            }
            else{
                swap(nums, p, p2);
                p2--;
            }
        }
    }

    //Majority Element II
    //time O(n)
    //space O(1)
    public static List<Integer> majorityElement2(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        for (int i = 0; i < nums.length; i++){
            if (candidate1 != null && nums[i] == candidate1){
                count1++;
            }
            else if (candidate2 != null && nums[i] == candidate2){
                count2++;
            }
            else if (count1 == 0){
                candidate1 = nums[i];
                count1++;
            }
            else if (count2 == 0){
                candidate2 = nums[i];
                count2++;
            }
            else{
                count1--;
                count2--;
            }
        }

        int finalCount1 = 0, finalCount2 = 0;
        int limit = nums.length /3;
        List<Integer> list = new ArrayList<>();

        for (int n: nums){
            if (candidate1 != null && n == candidate1)
                finalCount1++;
            if (candidate2 != null && n == candidate2)
                finalCount2++;

        }

        if (finalCount1 > limit){
            list.add(candidate1);
        }
        if (finalCount2 > limit){
            list.add(candidate2);
        }

        return list;
    }

    //H-Index
    //time O(n): because step 1 will traverse the citations array, so it is O(n); and step 2 at most will traverse the n times, so total 2 * O(n)
    //space O(n): because the new array length is n + 1, so it will be O(n)
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];

        //1.calculate the number of every elements in citations, but if one element bigger than the length of citations array, this element will be calculated on the element which its idx equals to the len of citations
        for (int c : citations){
            papers[Math.min(c, n)]++;
        }

        //2.sum is the sum of papers elements from right to left when k > sum
        int k = n;
        for (int sum = papers[n]; k > sum; sum += papers[k]){
            k--;
        }

        return k;
    }

    //349. Intersection of Two Arrays
    //time O(n + m): if n is nums1's length, m is nums2's length
    //space O(n + m): worst case suppose all unique elements
    public static int[] intersection(int[] nums1, int[] nums2) {
        //1.two hashset
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int n: nums1){
            set1.add(n);
        }

        for (int n: nums2){
            set2.add(n);
        }

        //2.method check if set1 contains set2 ele
        if (set1.size() < set2.size()){
            return checkSets(set1, set2);
        }
        else{
            return checkSets(set2, set1);
        }
    }

    private static int[] checkSets(HashSet<Integer> set1, HashSet<Integer> set2){
        int[] ans = new int[set1.size()];

        int index = 0;
        for (Integer s: set1){
            if (set2.contains(s)){
                ans[index++] = s;
            }
        }

        return Arrays.copyOf(ans, index);
    }

    //658. Find K Closest Elements
    //time O(logn - k) + k
    //space O(1)space used for the output does not count towards the space complexity
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right){
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = left; i < left + k; i++){//this step show why we need use arr.len - k as right boundary, because after binary search, we will attend left + k elements
            list.add(arr[i]);
        }

        return list;
    }

    //767. Reorganize String - Greedy
    //time O(n + ∑), ∑ refers to the sum of 26 letters
    //space O(∑)
    public static String reorganizeString(String s) {
        int len = s.length();
        int[] count = new int[26];
        int maxCount = 0;
        //corner case
        if (len <= 2){
            return s;
        }

        //1. count every unique elment's number
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            count[c - 'a']++;
            maxCount = Math.max(maxCount, count[c - 'a']);
        }

        if (maxCount > (len + 1) / 2){
            return "";
        }

        //2. reorganize all ele to odd - even idx
        char[] ans = new char[len];
        int oddIdx = 1, evenIdx = 0;

        for (int j = 0; j < count.length; j++){
            char ch = (char)(j + 'a');

            while (count[j] > 0 && count[j] <= len / 2 && oddIdx < len){
                ans[oddIdx] = ch;
                count[j]--;
                oddIdx += 2;
            }
            while(count[j] > 0){
                ans[evenIdx] = ch;
                count[j]--;
                evenIdx += 2;
            }
        }

        return new String(ans);
    }

    //791. Custom Sort String - Greedy
    //time: O(order.length + s.length)
    //space: O(s.length) - StringBuilder
    public static String customSortString(String order, String s) {
        //count the number of each unique element
        int[] count = new int[26];

        for (char c: s.toCharArray()){
            count[c - 'a']++;
        }

        //use StringBuilder to build new string
        StringBuilder sb = new StringBuilder();

        for (char c: order.toCharArray()){
            for (int i = 0; i < count[c - 'a']; ++i){
                sb.append(c);
            }
            count[c - 'a'] = 0;
        }

        //append others ele not included in order
        for (char i = 'a'; i <= 'z'; ++i){
            for (int j = 0; j < count[i - 'a']; ++j){
                sb.append(i);
            }
        }

        return sb.toString();
    }

    //969. Pancake Sorting
    //time: O(n^2)
    //space: O(n)
    public static List<Integer> pancakeSort(int[] A) {
        /*
        Input: arr = [3,2,4,1]
        Output: [4,2,4,3]
         */

        List<Integer> res = new ArrayList<>();
        if (A.length < 2){
            return res;
        }

        int len = A.length, largest = len;

        for (int i = 0; i < A.length; i++){
            //1.find the corresponding largest element's index in A
            int idx = find(A, largest);
            //2.flip 0 - index part, let the largest element move to 1st one in arr
            filp(A, 0, idx);
            //3.filp 0 - nth largest part, let the largest element move to the end in arr
            filp(A, 0, largest - 1);
            res.add(idx + 1); //why add idx + 1, what it refer to?
            res.add(largest);
            largest--;
        }

        return res;
    }

    private static int find(int[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == target){
                return i;
            }
        }
        return -1;
    }

    private static void filp(int[] arr, int start, int end){
        if (start == end){
            return;
        }

        int i = 0, j = end;
        while (i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    //1636. Sort Array by Increasing Frequency
    //time: nlogn
    //space: n
    public static int[] frequencySort(int[] nums) {
        //1.cal freq
        //n
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            int freq = map.getOrDefault(num, 0) + 1;
            map.put(num, freq);
        }

        //n
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b - a);
        for (int num: nums){
            que.offer(num);
        }

        //nlogn
        int index = 0;
        while (!que.isEmpty()){
            nums[index++] = que.poll();
        }

        return nums;
    }

    //692. Top K Frequent Words
    //time: O(n + logn)
    //space O(logn + k)
    public static List<String> topKFrequent(String[] words, int k) {
        //1.map cal each unique string's count num - O(n)
        HashMap<String, Integer> map = new HashMap<>();
        for (String word: words){
            int freq = map.getOrDefault(word, 0) + 1;
            map.put(word, freq);
        }

        //2.PriorityQueue sort - (logn)
        PriorityQueue<String> que = new PriorityQueue<>(new Comparator<String>() {
            public int compare(String a, String b){
                if (map.get(a) != map.get(b)){
                    return map.get(a) - map.get(b);
                }else{
                    return b.compareTo(a);
                }
            }
        });

        for (String word: map.keySet()){
            que.offer(word);
            if (que.size() > k){
                que.poll();
            }
        }

        //3.pop - O(logn)
        List<String> list = new ArrayList<>();
        while (!que.isEmpty()){
            list.add(0, que.poll());
        }

        return list;
    }
}
