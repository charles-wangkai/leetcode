import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][] minCosts = new int[n + 1][target + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(minCosts[i], Integer.MAX_VALUE);
        }
        minCosts[0][0] = 0;

        for (int h = 0; h < m; ++h) {
            int[][] nextMinCosts = new int[n + 1][target + 1];
            for (int i = 0; i <= n; ++i) {
                Arrays.fill(nextMinCosts[i], Integer.MAX_VALUE);
            }

            for (int i = 1; i <= n; ++i) {
                if (houses[h] != 0 && i != houses[h]) {
                    continue;
                }

                for (int j = 1; j <= target; ++j) {
                    for (int prevI = 0; prevI <= n; ++prevI) {
                        int prevJ = (prevI == i) ? j : (j - 1);

                        if (minCosts[prevI][prevJ] != Integer.MAX_VALUE) {
                            nextMinCosts[i][j] = Math.min(nextMinCosts[i][j],
                                    minCosts[prevI][prevJ] + ((houses[h] == 0) ? cost[h][i - 1] : 0));
                        }
                    }
                }
            }

            minCosts = nextMinCosts;
        }

        int[][] minCosts_ = minCosts;
        return IntStream.range(0, minCosts.length).filter(i -> minCosts_[i][target] != Integer.MAX_VALUE)
                .map(i -> minCosts_[i][target]).min().orElse(-1);
    }
}