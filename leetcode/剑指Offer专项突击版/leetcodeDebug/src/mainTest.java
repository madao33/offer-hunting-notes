import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] height = new int[]{1, 2, 3, 4, 5};
        int res = new Solution().minSubArrayLen(15, height);
        System.out.println(res);

    }
}
