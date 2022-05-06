class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return nums[0];

        int p = 0, q = nums[0];
        for (int i = 1; i < n; i++) {
            int temp = q;
            q = Math.max(nums[i] + p, q);
            p = temp;
        }
        return q;
    }
}