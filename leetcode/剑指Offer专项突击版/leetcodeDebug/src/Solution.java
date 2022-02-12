import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int sl = s.length(), pl = p.length();
        if(sl < pl) return res;

        int[] cnt = new int[26];
        for(int i = 0; i < pl; i++) {
            --cnt[p.charAt(i) - 'a'];
            ++cnt[s.charAt(i) - 'a'];
        }
        int diff = 0;
        for(int c : cnt) {
            diff += (c == 0 ? 0 : 1);
        }
        if(diff == 0) res.add(0);
        for(int i = 0; i < sl - pl; i++) {
            int x = s.charAt(i + pl) - 'a', y = s.charAt(i) - 'a';
            if(x == y){
                if(diff==0) res.add(i + 1);
                continue;
            }
            if(cnt[x] == 0) ++diff;
            ++cnt[x];
            if(cnt[x] == 0) --diff;
            if(cnt[y] == 0) ++diff;
            --cnt[y];
            if(cnt[y] == 0) --diff;
            if(diff == 0) res.add(i + 1);
        }
        return res;
    }
}