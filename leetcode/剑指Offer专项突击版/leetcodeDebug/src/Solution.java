class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return 0;
        int t = 0, ans = 0, d = nums[0] - nums[1];
        for (int i = 2; i < n; i++) {
            if (nums[i-1] - nums[i] == d) {
                t++;
            } else {
                d = nums[i-1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}