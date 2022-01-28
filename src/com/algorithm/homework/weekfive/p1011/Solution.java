package com.algorithm.homework.weekfive.p1011;


import java.util.Arrays;

/**
 * @author qiuch
 * p410分割数组类似问题，二分答案
 * Time complexity : O（Nlog（sum-max）+N（求sum）+N（求max）（手写max sum可以合并成N））->O(NlogN)
 * Space complexity : O(1)
 */
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //一天运完需要全部的重量
        if (days == 1) {
            return Arrays.stream(weights).sum();
        }
        //二分的区间为数组的最大元素～数组的和
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left < right) {
            int mid = (left + right) / 2;
            if (validate(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 转化为二分答案的验证函数
     * 是否能够以某个和(minWeight)把数组分成days个子数组
     *
     * @return
     */
    private boolean validate(int[] weights, int days, int minWeight) {
        int dailyWeight = 0;
        int useDays = 1;
        for (int weight : weights) {
            dailyWeight += weight;
            if (dailyWeight > minWeight) {
                useDays++;
                dailyWeight = weight;
            }
            if (useDays > days) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15));
    }
}