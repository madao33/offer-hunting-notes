import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int target = -nums[i];
            int l = i+1, r = n - 1;
            while(l < r) {
                if (nums[l] + nums[r] < target) {
                    l++;
                }else if(nums[l] + nums[r] > target) {
                    r--;
                }else {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    ans.add(temp);
                    break;
                }
            }
        }
        return ans;
    }
}