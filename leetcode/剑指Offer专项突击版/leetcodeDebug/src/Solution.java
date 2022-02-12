class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return 0;
        int low = 0, up = 0, sum = 0, ans = 0;
        while(up < nums.length) {
            sum += nums[up++];
            while(sum >= k) {
                if(sum == k) ans += 1;
                sum -= nums[low++];
            }
        }
        return ans;
    }
}
