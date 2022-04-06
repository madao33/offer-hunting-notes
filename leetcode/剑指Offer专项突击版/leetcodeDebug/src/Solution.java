class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int l = -1, r = n - 1, row = 0;
        // 查找是否存在行
        while(l < r) {
            int mid = (r - l + 1) / 2 + l;
            if (matrix[mid][0] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        row = l;
        if (row < 0)
            return false;

        // 查找列
        l = 0;
        r = m - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (matrix[row][mid] == target)
                return true;
            if (matrix[row][mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return false;

    }
}