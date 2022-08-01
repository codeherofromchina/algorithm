package leetcode;

class Solution4 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        double medianSortedArrays = findMedianSortedArrays(arr1, arr2);
        System.out.println(medianSortedArrays);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] minLenArr = nums1.length < nums2.length ? nums1 : nums2;
        int[] maxLenArr = nums1.length < nums2.length ? nums2 : nums1;
        int n = minLenArr.length + maxLenArr.length;
        if ((n & 1) == 1) {
            return process(minLenArr, maxLenArr, (n + 1) >> 1);
        } else {
            return (process(minLenArr, maxLenArr, n >> 1) + process(minLenArr, maxLenArr, (n >> 1) + 1)) / 2.0;
        }


    }


    public static int process(int[] minLenArr, int[] maxLenArr, int k) {
        if (minLenArr.length == 0) {
            return maxLenArr[k - 1];
        }
        if (k <= minLenArr.length) {
            return medianNum(minLenArr, 0, k - 1, maxLenArr, 0, k - 1);
        }
        if (maxLenArr[k - minLenArr.length - 1] >= minLenArr[minLenArr.length - 1]) {
            return maxLenArr[k - minLenArr.length - 1];
        }

        if (k <= maxLenArr.length) {
            return medianNum(minLenArr, 0, minLenArr.length - 1, maxLenArr, k - minLenArr.length, k - 1);
        } else {
            if (minLenArr[k - maxLenArr.length - 1] >= maxLenArr[maxLenArr.length - 1]) {
                return minLenArr[k - maxLenArr.length - 1];
            }
            return medianNum(minLenArr, k - maxLenArr.length, minLenArr.length - 1, maxLenArr, k - minLenArr.length, maxLenArr.length - 1);
        }
    }


    public static int medianNum(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
        if (l1 == r1) {
            return Math.min(nums1[l1], nums2[l2]);
        }

        while (l1 < r1 && l2 < r2) {
            int k = r1 - l1 + 1;
            int mid1 = l1 + ((k - 1) >> 1);
            int mid2 = l2 + ((k - 1) >> 1);
            if (nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }

            if (nums1[mid1] > nums2[mid2]) {
                r1 = mid1;
                l2 = mid2 + ((k & 1) == 0 ? 1 : 0);
            } else {
                r2 = mid2;
                l1 = mid1 + ((k & 1) == 0 ? 1 : 0);
            }
        }

        return Math.min(nums1[l1], nums2[l2]);
    }
}