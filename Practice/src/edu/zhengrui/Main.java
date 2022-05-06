package edu.zhengrui;

import java.util.Arrays;
import java.util.HashMap;

/*
       1 2 3 4 6
             i
             j
r_sum 10
l_sum 10

 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        System.out.println(reviewBalancedArray(arr));

        String[] deviceNames = {"switch", "tv", "switch", "tv","switch", "tv"};
        System.out.println(Arrays.toString(deviceNamesSystem(deviceNames)));
//        try {
//            int x = 0;
//            for (x = 1; x < 4; x++) {
//                System.out.println(x);
//            }
//        }
//        catch(Exception e){}
//        finally{
//            System.out.println("Error");
//        }
//        add(5);
    }
/*
1 2 3 4 6
    s
        e
leftSum nums[start]
rightSum nums[end]

while（start < end)

if (leftSum == rightSum)
    return start

while leftSum < rightSum && start < end
start++
leftSum += nums[start]

while rightSum < leftSum && start < end
end--
rightSum += nums[end]

start++
end--

return -1

 */

    private static int factorsArePalindrome(int num){
        //get all factors
        //check the factor is palindrome or not
        int count = 0;

        for (int i = 1; i <= num; ++i) {
            if (num % i == 0){
                boolean isPalindrome = checkPalindrome(i);
                if (isPalindrome){
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean checkPalindrome(int i){
        String str = Integer.toString(i);
        int n = str.length();

        for (int j = 0; j < n / 2; j++) {
            if (str.charAt(i) != str.charAt(n - i - 1)){
                return false;
            }
        }
        return true;
    }
//    private static void add(int a){
//        for (int i = 1; i < 3; i++) {
//            for (int j = 1; j < 3; j++) {
//                if (a == 5){
//                    break;
//                }
//                System.out.println(i * j);
//            }
//        }
//    }
    /*
    art, tv, art, tv, art, tv
    |
    ans [n]{}
    map
    k: string, v: count
    for i(String str: devicement)
    if (count > 0){
        ans[i] = str + Integer.toString(count);
    }else{
        ans[i] = str;
    }
    if not in map, put(str, +1)

     */

    private static String[] deviceNamesSystem(String[] deviceNames){
        int n = deviceNames.length;

        if (n == 1){
            return deviceNames;
        }

        String[] uniqueDeviceNames = new String[n];
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(deviceNames[i])){
                uniqueDeviceNames[i] = deviceNames[i];
                map.put(deviceNames[i], 1);
            }else {
                uniqueDeviceNames[i] = deviceNames[i] + map.get(deviceNames[i]);
                map.put(deviceNames[i], map.get(deviceNames[i]) + 1);
            }
        }

        return uniqueDeviceNames;
    }

    private static int reviewBalancedArray(int[] arr){
        //rightSum from n - 1 to 0, leftSum from 0 - n -1
        //when lS < rS, l++, lS + arr[i]; else when
        //if equal, return i
        int n = arr.length;
        int i = 0, j = n - 1;
        int leftSum = arr[i], rightSum = arr[j];

        while (i < j){
            while (leftSum < rightSum && i < j){
                i++;
                leftSum += arr[i];
            }

            while (rightSum < leftSum && i < j){
                j--;
                rightSum -= arr[i];
            }

            i++;
            j--;
        }

        if (i == j && leftSum == rightSum){
            return i;
        }

        return -1;
    }


    private static int findPivot2RightSumLeftSum(int[] arr){
        int n = arr.length;
        int leftSum = 0, rightSum = 0;

        for (int i = 1; i < n; i++) {
            rightSum += arr[i];
        }

        for (int i = 0, j = 1; i < n; i++, j++) {
            leftSum += arr[i];
            rightSum -= arr[j];

            if (leftSum == rightSum){
                return j;
            }
        }

        return -1;
    }

    private static int findPivot1PrefixSuffix(int[] arr){
        int n = arr.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        suffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] += suffix[i + 1] + arr[i];
        }

        for (int i = 0; i < n; i++) {
            if (suffix[i] == prefix[i]){
                return i;
            }
        }

        return -1;
    }
}
