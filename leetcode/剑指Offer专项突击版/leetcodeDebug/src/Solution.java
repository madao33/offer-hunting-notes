class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len < 2)
            return strs[0];

        int resLen = strs[0].length();
        char[] s1 = strs[0].toCharArray();
        for (int i = 1; i < len; i++) {
            char[] s2 = strs[i].toCharArray();
            resLen = Math.min(resLen, s2.length);
            for (int j = 0; j < resLen; j++) {
                if (s1[j] == s2[j])
                    continue;
                else {
                    resLen = j;
                    break;
                }
            }
        }
        String res = strs[0].substring(0, resLen);
        return res;
    }
}