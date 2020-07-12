class Solution {
    public int numSub(String s) {
        long result = 0;
        int count1 = 0;
        for (int i = 0; i <= s.length(); ++i) {
            if (i != s.length() && s.charAt(i) == '1') {
                ++count1;
            } else {
                result += count1 + count1 * (count1 - 1L) / 2;

                count1 = 0;
            }
        }

        return (int) (result % 1_000_000_007);
    }
}