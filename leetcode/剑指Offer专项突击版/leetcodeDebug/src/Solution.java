class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1, res = Math.min(nums[r], nums[l]);
        while(l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= nums[0])
                l = mid + 1;
            else
                r = mid;
        }
        return Math.min(res, nums[l]);
    }
}