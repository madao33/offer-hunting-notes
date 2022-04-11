class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1, left = 0, acc = 0;
        for (int right = 0; right < nums.length; right++) {
            acc += nums[right];

            while(acc >= target) {
                res = Math.min(res, right - left + 1);
                acc -= nums[left++];
            }
        }

        return res < nums.length + 1 ? res : 0;
    }
}