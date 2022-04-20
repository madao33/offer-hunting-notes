import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int res = new Solution().lengthOfLIS(nums);
        System.out.println(res);

    }
}
