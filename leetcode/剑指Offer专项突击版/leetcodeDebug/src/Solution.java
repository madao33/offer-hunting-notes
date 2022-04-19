class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];

        int right = 0, left = 0, pre = 0;
        // 第一次遍历
        while (left < n) {
            while (right < n && s.charAt(right) != c)
                right++;

            if (right >= n)
                break;

            while (left <= right) {
                res[left] = right - left;
                left++;
            }
            pre = right;
            right++;


        }

        right = n - 1;
        left = n - 1;
        while (right > 0) {
            while (left >= 0 && s.charAt(left) != c)
                left--;

            if (left < 0)
                break;

            while (right >= left) {
                res[right] = Math.min(right - left, res[right]);
                right--;
            }
            left--;

        }

        return res;
    }
}