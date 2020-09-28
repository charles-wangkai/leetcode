import java.util.Arrays;

class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int result = 0;
        for (int code = 0; code < 1 << requests.length; ++code) {
            int[] delta = new int[n];
            for (int i = 0; i < requests.length; ++i) {
                if ((code & (1 << i)) != 0) {
                    --delta[requests[i][0]];
                    ++delta[requests[i][1]];
                }
            }

            if (Arrays.stream(delta).allMatch(x -> x == 0)) {
                result = Math.max(result, Integer.bitCount(code));
            }
        }

        return result;
    }
}