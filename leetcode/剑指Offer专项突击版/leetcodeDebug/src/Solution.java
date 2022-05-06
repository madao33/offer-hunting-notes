class Solution {
    public boolean isPowerOfThree(int n) {
        while(n > 0) {
            if (n % 3 != 0)
                return false;
            n /= 3;
        }
        return true;
    }
}