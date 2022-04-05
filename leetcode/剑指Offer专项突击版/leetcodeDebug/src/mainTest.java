import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] res = new Solution().searchRange(nums, 8);
        for (int r : res)
            System.out.print(r + "\t");

    }
}


