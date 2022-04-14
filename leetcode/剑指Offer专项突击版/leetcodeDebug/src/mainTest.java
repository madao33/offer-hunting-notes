import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        List<List<Integer>> res = new Solution().combinationSum2(candidates, 8);
        System.out.println(res);

    }
}
