/*
rotate array 为什么返回2，不是3
153. Find Minimum in Rotated Sorted Array


 */

public class Main {
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(binarySearchInterative(arr, 7));
        int[] arr1 = {0, 0, 0, 0, 1, 1,}
    }

    private static boolean binarySearchInterative(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return false;
        }
        int start = 0, end = arr.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (arr[mid] == x){
                return true;
            }
            else if (arr[mid] > x){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }

        return false;
    }

    private static boolean binarySearchRecursive(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return false;
        }
        return binarySearchRecursive(arr, x, 0, arr.length - 1);
    }

    //O(
    private static boolean binarySearchRecursive(int[] arr, int x, int start, int end){
        if (start > end){
            return false;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == x){
            return true;
        }
        else if (arr[mid] < x){
            return binarySearchRecursive(arr, x, mid + 1, end);
        }
        else{
            return binarySearchRecursive(arr, x, start, mid - 1);
        }
    }

    //O(logn)
    private static int findFirstOccurance(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return -1;
        }
        return findFirstOccurance(arr, x, 0,arr.length - 1);
    }


    private static int findFirstOccurance(int[] arr, int x, int start, int end) {
        if (arr[start] > x || arr[end] < x){
            return -1;
        }
        if (arr[start] == x){
            return start;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == x){
            return findFirstOccurance(arr, x, start, mid);
        }
        else if (arr[mid] < x){
            return findFirstOccurance(arr, x, mid + 1, end);
        }
        else{
            return findFirstOccurance(arr, x, start, mid - 1);
        }
    }

    private static int findLastOccurance(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return -1;
        }
        return findFirstOccurance(arr, x, 0,arr.length - 1);
    }

    private static int findLastOccurance(int[] arr, int x, int start, int end) {
        if (arr[start] > x || arr[end] < x){
            return -1;
        }
        if (arr[end] == x){
            return end;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == x){
            return findFirstOccurance(arr, x, mid, end);
        }
        else if (arr[mid] < x){
            return findFirstOccurance(arr, x, mid + 1, end);
        }
        else{
            return findFirstOccurance(arr, x, start, mid - 1);
        }
    }

    private static int findTotalOccurance(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return 0;
        }

        int first = findFirstOccurance(arr, x);
        if (first == -1){
            return 0;
        }
        int last = findLastOccurance(arr, x);

        return (last - first + 1);
    }

    private static int findTotalOccuranceRecursive(int[] arr, int x){
        if (arr == null || arr.length == 0){
            return 0;
        }

        return findTotalOccuranceRecursive(arr, x, 0, arr.length - 1);
    }

    private static int findTotalOccuranceRecursive(int[] arr, int x, int start, int end){
        if (arr[start] > x || arr[end] < x){
            return 0;
        }
        if (arr[start] == x && arr[end] == x){
            return (end - start + 1);
        }

        int mid = (start + end) / 2;
        if (arr[mid] == x){
            return 1 + findTotalOccuranceRecursive(arr, x, start, mid - 1)
                    + findTotalOccuranceRecursive(arr, x, mid + 1, end);
        }
        else if (arr[mid] < x){
            return findTotalOccuranceRecursive(arr, x, mid + 1, end);
        }
        else{
            return findTotalOccuranceRecursive(arr, x, start, mid - 1);
        }
    }

    private static void reverse(int[] arr, int start, int end){
        if (arr == null || arr.length == 0 || start >= end || start < 0 || end > arr.length - 1){
            return;
        }

        while (start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static void rotateArray(int[] arr, int k){
        if (arr == null || arr.length <= 1){
            return;
        }
        k = k % arr.length;

        //1.reverse entire array
        reverse(arr, 0, arr.length - 1);
        //2.reverse 0 - k - 1, k - arr.len - 1
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    private static int findRotatedIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1){
            return 0;
        }


        return findRotatedIndex(arr, 0, arr.length - 1);

    }

    private static int findRotatedIndex(int[] arr, int start, int end){
        //no duplicates
        if (arr[start] == arr[end]){
            return start;
        }

        int mid = (start + mid) / 2;
        if (arr[mid] > arr[start]){
            //left part properly sorted
            return findRotatedIndex(arr, mid, end);
        }else{
            //right part properly
            return findRotatedIndex(arr, start, mid);
        }
    }



}
