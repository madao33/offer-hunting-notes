import java.util.List;

public class mainTest {
    public static void main(String[] args){
        String s = "abab", p = "ab";
        List<Integer> res = new Solution().findAnagrams(s, p);
        System.out.println(res);
    }
}
