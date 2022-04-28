import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target-nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(nums[i], -1) != -1 && map.getOrDefault(nums[i], -1) != i) {
                res[0] = map.get(nums[i]);
                res[1] = i;
                break;
            }
        }

        return res;
    }
}