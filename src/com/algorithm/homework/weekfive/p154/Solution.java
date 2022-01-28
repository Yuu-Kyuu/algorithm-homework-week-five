package com.algorithm.homework.weekfive.p154;


/**
 * @author qiuch
 * 例题P153的扩展，主要是重复元素
 * Time complexity : O(logN)
 * Space complexity : O(1)
 */
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                //由于有重复元素当左右相等时，需要单个逼近看是否还有下降的趋势
                //下降趋势只会出现在从右向左的过程中,否则就会一直相等，直到left，right相遇
                //此处逼近是O（N）的操作，会对整个logn的复杂度产生影响
                right -= 1;
            }
        }
        return nums[left];
    }


    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3,}));
    }
}