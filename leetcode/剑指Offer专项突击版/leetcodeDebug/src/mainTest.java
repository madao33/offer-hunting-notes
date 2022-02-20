import java.util.List;

public class mainTest {
    public static void main(String[] args){
//        String[] words = new String[]{"hello", "leetcode"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";
//        boolean res = new Solution().isAlienSorted(words, order);
        int num1 = 1, num2 = -3;
        System.out.println(isSameSign(num1, num2));

    }

    public static boolean isSameSign(int num1, int num2) {
        return num1 * num2 >= 0;
    }
}
