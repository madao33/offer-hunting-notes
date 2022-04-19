import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> hashSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j < n; j++)
                if (dp[j] && hashSet.contains(s.substring(i, j))) {
                    dp[i] = true;
                    break;
                }
        }
        return dp[n];
    }
}