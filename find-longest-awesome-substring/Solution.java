import java.util.Arrays;

class Solution {
    public int longestAwesome(String s) {
        int[] minIndices = new int[1 << 10];
        Arrays.fill(minIndices, Integer.MAX_VALUE);
        minIndices[0] = -1;

        int result = -1;
        int code = 0;
        for (int i = 0; i < s.length(); ++i) {
            int digit = s.charAt(i) - '0';
            code ^= 1 << digit;

            for (int j = 0; j < 10; ++j) {
                int prevCode = code ^ (1 << j);
                if (minIndices[prevCode] != Integer.MAX_VALUE) {
                    result = Math.max(result, i - minIndices[prevCode]);
                }
            }

            if (minIndices[code] != Integer.MAX_VALUE) {
                result = Math.max(result, i - minIndices[code]);
            } else {
                minIndices[code] = i;
            }
        }

        return result;
    }
}