public class Solution {
    public String sortString(String s) {
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            ++counts[ch - 'a'];
        }

        StringBuilder result = new StringBuilder();
        int rest = s.length();
        while (rest != 0) {
            for (int i = 0; i < counts.length; ++i) {
                if (counts[i] != 0) {
                    result.append((char) (i + 'a'));
                    --counts[i];
                    --rest;
                }
            }
            for (int i = counts.length - 1; i >= 0; --i) {
                if (counts[i] != 0) {
                    result.append((char) (i + 'a'));
                    --counts[i];
                    --rest;
                }
            }
        }

        return result.toString();
    }
}