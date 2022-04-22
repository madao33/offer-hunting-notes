import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] coins = new int[]{186, 419, 83, 408};
        int res = new Solution().coinChange(coins, 6249);
        System.out.println(res);

    }
}
