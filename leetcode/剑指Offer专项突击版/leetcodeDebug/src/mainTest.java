import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> res = new Solution().threeSum(nums);
        System.out.println(res);

    }
}


