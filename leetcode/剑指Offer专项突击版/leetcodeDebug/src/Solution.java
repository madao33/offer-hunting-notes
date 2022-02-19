
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] alph = new int[26];
        for (int i = 0; i < order.length(); i++)
            alph[order.charAt(i) - 'a'] = i + 1;
        for(int i = 0; i < words.length-1; i++) {
            if (!isAlph(words[i], words[i+1], alph))
                return false;
        }
        return true;
    }

    public boolean isAlph(String A, String B, int[] alph) {
        int index = 0;
        while(index < A.length() && index < B.length()) {
            if (alph[A.charAt(index) - 'a'] < alph[B.charAt(index) - 'a'])
                return true;
            if (alph[A.charAt(index) - 'a'] > alph[B.charAt(index) - 'a'])
                return false;
            index++;
        }
        if (index < A.length()) return false;
        return true;
    }
}