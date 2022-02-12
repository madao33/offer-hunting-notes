public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{1, 2, 3};
        int k = 3;
        int res = new Solution().subarraySum(nums, k);
        System.out.println(res);
    }
}
