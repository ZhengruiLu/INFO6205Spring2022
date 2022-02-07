import java.util.*;
/*
Assignment 2: Searching and Sorting Continuity
Search Insert Position (https://leetcode.com/problems/search-insert-position/ (Links to an external site.))
Single Element in a Sorted Array (https://leetcode.com/problems/single-element-in-a-sorted-array/ (Links to an external site.) )
Find Minimum in Rotated Sorted Array ( https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ (Links to an external site.) )
Meeting Rooms II (https://leetcode.com/problems/meeting-rooms-ii/ (Links to an external site.) )
Top K Frequent Elements (https://leetcode.com/problems/top-k-frequent-elements/ (Links to an external site.) )
3Sum Closest (https://leetcode.com/problems/3sum-closest/ (Links to an external site.) )
Insert Interval (https://leetcode.com/problems/insert-interval/ (Links to an external site.) )
Non-overlapping Intervals (https://leetcode.com/problems/non-overlapping-intervals/ (Links to an external site.))
Interval List Intersections ( https://leetcode.com/problems/interval-list-intersections/ (Links to an external site.))
4Sum (https://leetcode.com/problems/4sum/ (Links to an external site.))
*/
public class Main {
    public static void main(String[] args){
        //q1
        int[] nums = {1,3,5,6};
        int target = 5;
        System.out.println(searchInsert(nums, target));

        int target2 = 2;
        System.out.println(searchInsert(nums, target2));

        //q2
        int[] nums2 = {1,1,2,3,3,4,4,8,8};//2
        System.out.println(singleNonDuplicate(nums2));

        //q3
        int[] nums3 = {3,4,5,1,2}; //1
        System.out.println(findMin(nums3));

        //q4
        int[] nums4 = {1,1,1,2,
                2,3};//[1,2] or [2,1]
        int k = 2;
        //solution1: heap
        System.out.println(Arrays.toString(topKFrequent(nums4, k)));
        //solution2: quickSelect
        System.out.println(Arrays.toString(topKFrequent2(nums4, k)));

        //q5
        int[][] intervals = {{0,30}, {5,10}, {15,20}};//2
        System.out.println(minMeetingRooms(intervals));

        //q6
        int[] nums6 = {-1,2,1,-4};
        int target6 = 1;
        System.out.println(threeSumClosest(nums6, target6));

        //q7
        int[][] intervals7 = {{1,3}, {6,9}};//[[1,5],[6,9]]
        int[] newInterval = {2,5};
        print2DArray(insert(intervals7, newInterval));

        //q8
        int[][] intervals8 = {{1,2}, {2,3}, {3,4}, {1,3}};//1
        System.out.println(eraseOverlapIntervals(intervals8));

        //q9
        int[][] firstList = {{0,2}, {5,10}, {13,23}, {24,25}};//[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        int[][] secondList = {{1,5}, {8,12}, {15,24}, {25,26}};
        print2DArray(intervalIntersection(firstList, secondList));

        //q10
        int[] nums10 = {1,0,-1,0,-2,2};
        int target10 = 0;//[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        List<List<Integer>> ans = fourSum(nums10, target10);
        print2DList(ans);
    }

    //helpers
    public static void print2DArray(int[][] matrix){
        for (int[] arr: matrix){
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void print2DList(List<List<Integer>> ans) {
        for (List<Integer> list: ans){
            System.out.println(list.toString());
        }
    }

    /*
    35. Search Insert Position
    binary search
    time O(log n), space O(1)
    */
    public static int searchInsert(int[] nums, int target) {
        //binary search
        int start = 0, end = nums.length - 1;
        int mid = 0;

        while(start <= end){
            mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return start;
    }

    /*
    540. Single Element in a Sorted Array

    binary search + even index
    len: 2 * n + 1 - odd - index final will be even
    mid - even, make sure it is the mid of searching part

    time: O(log n/2) = O(log n), space: O(1)
    */
    public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        int mid = 0;

        while (start < end){
            mid = start + (end - start) / 2;
            if (mid % 2 == 1){
                mid--;
            }
            if (nums[mid] == nums[mid + 1]){
                start = mid + 2;
            }
            else{
                end = mid;
            }
        }

        return nums[start];
    }

    /*
    153. Find Minimum in Rotated Sorted Array
    binary search
    time: O(log n), space: O(1)
    */
    public static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        int mid = 0;

        if (nums[start] < nums[end]){
            return nums[start];
        }

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (nums[start] > nums[mid]){//mid in the smaller sorted part, so find ans in left
                end = mid;
            }else{//mid in the larger sorted part, so find ans in right
                start = mid;
            }
        }

        return nums[end];
    }

    /*
    347. Top K Frequent Elements

    Solution 1
    HashMap - calculate the frequency of each unique elements
    PriorityQueue - custom sort, keep k size
    put que elements into ans

    time: O(n * log k), space: O(n + k) - n-size map, k-size heap
    */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //O(n)
        for (int num: nums){
            int freq = map.getOrDefault(num, 0) + 1;
            map.put(num, freq);
        }

        //2.use PriorityQueue to do custom sort - unique ele number - n * log k
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> (map.get(a) - map.get(b)));//small - big
        for (Integer num: map.keySet()){
            que.offer(num);
            if (que.size() > k){//keep size k, reduce the size of que
                que.poll();
            }
        }

        //3.ans - k * log k , k times for loop, each time poll one, log k custom sort
        int[] ans = new int[k];
        for (int i = k - 1; i > -1; i--){
            ans[i] = que.poll();
        }

        return ans;
    }

    /*
    347. Top K Frequent Elements
    Solution 2: quickSelect
    time: average O(n), worst O(n^2), space: O(n)
    */
    static int[] uniqueEle;
    static Map<Integer, Integer> map;

    public static int[] topKFrequent2(int[] nums, int k) {
        //k - num, v - freq
        map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //get unique elements array
        int n = map.size();
        uniqueEle = new int[n];
        int i = 0;
        for (int num: map.keySet()){
            uniqueEle[i] = num;
            i++;
        }
        //keep pivot as wall, left part before pivot will be less frequency part, right will be more frequency part, use n - k to get ans
        quickSelect(0, n - 1, n - k);
        return Arrays.copyOfRange(uniqueEle, n - k, n);
    }

    public static void quickSelect(int left, int right, int kSmallest){
        if (left == right){
            return;
        }

        Random random = new Random();
        int pivotIdx = left + random.nextInt(right - left);
        pivotIdx = partition(left, right, pivotIdx);

        if (kSmallest == pivotIdx){
            return;
        }
        else if (kSmallest < pivotIdx){
            quickSelect(left, pivotIdx - 1, kSmallest);
        }
        else{
            quickSelect(pivotIdx + 1, right, kSmallest);
        }
    }

    public static int partition(int left, int right, int pivotIdx){
        int pivotFreq = map.get(uniqueEle[pivotIdx]);

        swap(pivotIdx, right);
        int storeIdx = left;

        for (int i = left; i <= right; i++){
            if (map.get(uniqueEle[i]) < pivotFreq){
                swap(i, storeIdx);
                storeIdx++;
            }
        }
        //move pivot to correct place as wall
        swap(storeIdx, right);
        return storeIdx;
    }

    public static void swap(int a, int b) {
        int tmp = uniqueEle[a];
        uniqueEle[a] = uniqueEle[b];
        uniqueEle[b] = tmp;
    }

    /*
    253. Meeting Rooms II
    heap, array sort
    time: O(nlogn), space: O(n)
    */
    public static int minMeetingRooms(int[][] intervals) {
        //sort array by the start time of each meeting, O(nlogn)
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(final int[] a, final int[] b){
                return a[0] - b[0];
            }
        });

        //use PriorityQueue keep intervals size elements, each end time, O(nlogn)
        PriorityQueue<Integer> que = new PriorityQueue<>(intervals.length, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a - b;
            }
        });

        que.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++){
            if (intervals[i][0] >= que.peek()){
                que.poll();
            }
            que.offer(intervals[i][1]);
        }

        return que.size();
    }
    private static void swap(int[] arr, int i , int j){
        if(arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length || i == j){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void moveElements( int[] arr, int from,  int to){
        int temp =arr[to];
        for(int i =to ; i >from; i --){
            swap(arr, i -1, i  );
        }
        arr[from] =temp;
    }

    /*
    16. 3Sum Closest
    Solution 1: two pointers
    minDiff = target - 3sum

    time: O(n^2), space:O(logn) - O(n)
    */
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < n && minDiff != 0;i++){
            int lo = i + 1, hi = n - 1;
            while (lo < hi){
                int sum = nums[lo] + nums[i] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(minDiff)){
                    minDiff = target - sum;
                }
                if (sum < target){
                    lo++;
                }else{
                    hi--;
                }
            }
        }
        return target - minDiff;
    }

    /*
    57. Insert Interval
    greedy
    time: O(n), space: O(n)
    */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> ans = new LinkedList<int[]>();
        int n = intervals.length;
        int idx = 0;
        int newStart = newInterval[0], newEnd = newInterval[1];

        //add arrays which start time less than newInterval to ans
        while (idx < n && intervals[idx][0] < newStart){
            ans.add(intervals[idx++]);
        }

        //add new interval into ans, merge it if need
        int[] interval = new int[2];
        if (ans.isEmpty() || ans.getLast()[1] < newStart){
            ans.add(newInterval);
        }
        else{
            interval = ans.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            ans.add(interval);
        }

        //add other intervals into ans, merge with new interval if need
        while (idx < n){
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            if (ans.getLast()[1] < start){
                ans.add(interval);
            }
            else{
                interval = ans.removeLast();
                interval[1] = Math.max(interval[1], end);
                ans.add(interval);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }

    /*
    435. Non-overlapping Intervals
    greedy
    time:O(nlogn), spaceO(1)
    */
    public static int eraseOverlapIntervals(int[][] intervals) {
        //sort intervals by its end
        Comparator<int[]> compareEnd = (a, b) -> (a[1] - b[1]);
        Arrays.sort(intervals, compareEnd);

        int end = intervals[0][1];
        int nonLapCount = 1;

        for (int i = 1; i < intervals.length; i++){
            if (intervals[i][0] >= end){
                end = intervals[i][1];
                nonLapCount++;
            }
        }

        return intervals.length - nonLapCount;
    }

    /*
    986. Interval List Intersections
    two pointers
    time:O(N + M), space:max O(N + M)
    N - firstList.length, M - secondList.length;
    */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length, m = secondList.length;
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m){
            //get intersections
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);

            if (lo <= hi){
                ans.add(new int[]{lo, hi});
            }

            if (firstList[i][1] < secondList[j][1]){//means no lap until curr element in firstList
                i++;
            }else{//means no lap until curr element in secondList
                j++;
            }
        }

        return ans.toArray(new int[ans.size()][]);//the array size already defined when comparing lo vs hi
    }

    /*
    18. 4Sum
    recursion + hashSet for twoSum
    time: O(n^k-1) is the sum of k - 2 times iterating over n elements, plus twoSum - O(n)
    space: O(n)
    */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        //sort it so we can use two sum
        Arrays.sort(nums);
        return kSum(nums, target, 0 , 4);
    }

    public static List<List<Integer>> kSum(int[] nums, int target, int start, int k){
        List<List<Integer>> res = new ArrayList<>();
        //check 3 conditions
        //a.Have we run out of numbers to choose from?
        if (start == nums.length){
            return res;
        }
        //b.vs average value, smallest bigger than it, or largest smaller than it, can just return res
        int average = target / k;
        if (nums[0] > average || nums[nums.length - 1] < average){
            return res;
        }
        //c.when k == 2, call twoSum
        if (k == 2){
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i){//this part need to focus more time
            if (i == start || nums[i - 1] != nums[i]){
                for (List<Integer> subList: kSum(nums, target - nums[i], i + 1, k - 1)){
                    //add new list including nums[i]
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    //add its corresponding subList
                    res.get(res.size() - 1).addAll(subList);
                }
            }
        }

        return res;
    }

    public static List<List<Integer>> twoSum(int[] nums, int target, int start){
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = start; i < nums.length; ++i){
            //if empty or the element not duplicate
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]){
                if (set.contains(target - nums[i])){
                    res.add(Arrays.asList(target - nums[i], nums[i]));
                }
                set.add(nums[i]);
            }
        }

        return res;
    }
}
