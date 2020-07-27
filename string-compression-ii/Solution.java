import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    static final int ALPHABET_SIZE = 26;

    public int getLengthOfOptimalCompression(String s, int k) {
        int[][][] minLengths = new int[k + 1][ALPHABET_SIZE + 1][s.length() + 1];
        for (int i = 0; i <= k; ++i) {
            for (int j = 0; j <= ALPHABET_SIZE; ++j) {
                Arrays.fill(minLengths[i][j], Integer.MAX_VALUE);
            }
        }
        minLengths[k][0][0] = 0;

        for (char ch : s.toCharArray()) {
            int[][][] nexMinLengths = new int[k + 1][ALPHABET_SIZE + 1][s.length() + 1];
            for (int i = 0; i <= k; ++i) {
                for (int j = 0; j <= ALPHABET_SIZE; ++j) {
                    Arrays.fill(nexMinLengths[i][j], Integer.MAX_VALUE);
                }
            }

            int value = ch - 'a' + 1;
            for (int i = 0; i <= k; ++i) {
                for (int j = 0; j <= ALPHABET_SIZE; ++j) {
                    for (int c = 0; c <= s.length(); ++c) {
                        if (minLengths[i][j][c] != Integer.MAX_VALUE) {
                            if (i != 0) {
                                nexMinLengths[i - 1][j][c] = Math.min(nexMinLengths[i - 1][j][c], minLengths[i][j][c]);
                            }

                            if (j == value) {
                                if (c != s.length()) {
                                    nexMinLengths[i][j][c + 1] = Math.min(nexMinLengths[i][j][c + 1],
                                            minLengths[i][j][c] + computeCountLength(c + 1) - computeCountLength(c));
                                }
                            } else {
                                nexMinLengths[i][value][1] = Math.min(nexMinLengths[i][value][1],
                                        minLengths[i][j][c] + 1);
                            }
                        }
                    }
                }
            }

            minLengths = nexMinLengths;
        }

        int[][][] minLengths_ = minLengths;
        return IntStream.rangeClosed(0, ALPHABET_SIZE).map(i -> Arrays.stream(minLengths_[0][i]).min().getAsInt()).min()
                .getAsInt();
    }

    int computeCountLength(int count) {
        return (count == 1) ? 0 : String.valueOf(count).length();
    }
}