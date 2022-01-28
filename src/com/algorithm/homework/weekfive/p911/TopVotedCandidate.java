package com.algorithm.homework.weekfive.p911;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiuch
 * Time complexity for q : O(logN)
 * Time complexity for TopVotedCandidate : O(N)
 * Space complexity : O(N)
 *
 * 由于times最值比较大，10^9,不然可以把每一个整数时间都最为一个key放map里，这样查询可以O(1)
 * 如果像例子一样是有规律的times，也可自己实现一个hash()函数，来把一段区间的值映射到某个key
 *
 */
class TopVotedCandidate {
    int[] times;
    int[] currentTimeTops;
    Map<Integer, Integer> personVoteCounts;

    public TopVotedCandidate(int[] persons, int[] times) {
        currentTimeTops = new int[times.length];
        personVoteCounts = new HashMap<>();
        //保护节点，维护初始的最小值
        personVoteCounts.put(-1, -1);
        //当前最大值的p
        int currentTop = -1;
        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i];
            personVoteCounts.put(p, personVoteCounts.getOrDefault(p, 0) + 1);
            if (personVoteCounts.get(p) >= personVoteCounts.get(currentTop)) {
                currentTop = p;
            }
            currentTimeTops[i] = currentTop;
        }
        this.times = times;
    }

    public int q(int t) {
        int left = 0, right = times.length - 1;
        // 二分查找第一个<=t的元素
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return currentTimeTops[left];
    }
}