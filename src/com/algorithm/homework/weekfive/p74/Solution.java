package com.algorithm.homework.weekfive.p74;


/**
 * @author qiuch
 * 二分查找目标行，然后在目标行中二分查找target
 * 也可把matrix看作行合并后的长数组做二分查找，效果一样
 * Time complexity : O(log n*m)
 * Space complexity : O(1)
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int upperRow = -1, lowerRow = matrix.length - 1;

        //二分查找第一个大于target的列头，target肯定属于前一列的区间
        while (upperRow < lowerRow) {
            int mid = (lowerRow - upperRow + 1) / 2 + upperRow;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                upperRow = mid;
            } else {
                lowerRow = mid - 1;
            }
        }

        if (upperRow == -1) {
            return false;
        }

        //二分查找目标行
        int[] targetRow = matrix[upperRow];
        int left = 0, right = targetRow.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (targetRow[mid] == target) {
                return true;
            } else if (targetRow[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        //System.out.println(new Solution().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(new Solution().searchMatrix(new int[][]{{1}, {3}}, 4));
    }
}