package com.algorithm.homework.weekfive.p875;


import java.util.Arrays;

/**
 * @author qiuch
 * 依旧二分答案
 * Time complexity : O（Nlog（max）+N（求max））->O(NlogN)
 * Space complexity : O(1)
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //二分的区间为1～数组最大值（一次只能吃一堆，再多也没用）
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        while (left < right) {
            int mid = (left + right) / 2;
            if (validate(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 转化为二分答案的验证函数
     * 是否能够以某个速度把香蕉在h小时内吃完
     *
     * @return
     */
    private boolean validate(int[] piles, int h, int k) {
        int useHours = 0;
        for (int pile : piles) {
            useHours = useHours + (pile % k == 0 ? pile / k : pile / k + 1);
            if (useHours > h) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }
}