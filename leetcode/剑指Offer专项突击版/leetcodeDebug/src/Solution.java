import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int n = coins.length, ans = 0;
        while(amount > 0) {
            if (amount < coins[0])
                return -1;
            for (int i = n - 1; i >= 0; i--) {
                if (amount >= coins[i]) {
                    amount -= coins[i];
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}