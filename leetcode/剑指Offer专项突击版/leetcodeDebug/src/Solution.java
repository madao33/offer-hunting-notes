import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, combine, ans);
        return ans;
    }

    public void dfs(int[] candidates, int target, int idx, List<Integer> combine, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return ;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] < 0)
                break;

            if (i > idx && candidates[i] == candidates[i-1])
                continue;

            combine.add(candidates[i]);
            System.out.println("递归之前=>" + combine + ", 剩余" + (target - candidates[i]));
            dfs(candidates, target - candidates[i], i + 1, combine, ans);
            combine.remove(combine.size() - 1);
            System.out.println("递归之后=>" + combine + ", 剩余" + (target));
        }

    }
}