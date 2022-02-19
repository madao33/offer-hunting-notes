import java.util.List;

public class mainTest {
    public static void main(String[] args){
        String[] words = new String[]{"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean res = new Solution().isAlienSorted(words, order);
    }
}
