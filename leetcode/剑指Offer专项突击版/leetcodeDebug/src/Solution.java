class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            int h = Math.min(height[l], height[r]);
            res = Math.max(res, h * (r-l));
            if (height[l+1] < height[r-1])
                r--;
            else
                l++;
        }
        return res;
    }
}