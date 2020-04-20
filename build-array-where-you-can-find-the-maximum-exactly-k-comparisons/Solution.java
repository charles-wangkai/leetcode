import java.util.stream.IntStream;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numOfArrays(int n, int m, int k) {
        int[][] wayNums = new int[m + 1][k + 1];
        wayNums[0][0] = 1;

        for (int p = 0; p < n; ++p) {
            int[][] nextWayNums = new int[m + 1][k + 1];
            for (int i = 1; i <= m; ++i) {
                for (int j = 0; j <= k; ++j) {
                    for (int prevI = 0; prevI <= i; ++prevI) {
                        if (prevI == i) {
                            nextWayNums[i][j] = addMod(nextWayNums[i][j], multiplyMod(wayNums[prevI][j], i));
                        } else if (j >= 1) {
                            nextWayNums[i][j] = addMod(nextWayNums[i][j], wayNums[prevI][j - 1]);
                        }
                    }
                }
            }

            wayNums = nextWayNums;
        }

        int[][] wayNums_ = wayNums;
        return IntStream.rangeClosed(1, m).map(i -> wayNums_[i][k]).reduce(0, this::addMod);
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }

    int multiplyMod(int x, int y) {
        return (int) ((long) x * y % MODULUS);
    }
}