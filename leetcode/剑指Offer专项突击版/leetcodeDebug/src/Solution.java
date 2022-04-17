import java.util.*;

class Solution {
    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length() <= 0)
            return ans;
        dfs(digits, 0, new StringBuffer(), ans);
        return ans;
    }

    public void dfs(String digits, int cur, StringBuffer path, List<String> ans) {
        if (cur == digits.length()) {
            ans.add(path.toString());
            return ;
        }
        char curchar = digits.charAt(cur);
        String letters = phoneMap.get(curchar);
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            path.append(letter);
            dfs(digits, cur + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }

    }
}