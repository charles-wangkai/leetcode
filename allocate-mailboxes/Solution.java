import java.util.Arrays;

class Solution {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;

        Arrays.sort(houses);

        int[][] distances = new int[n][n];
        for (int length = 1; length <= n; ++length) {
            for (int beginIndex = 0; beginIndex + length <= n; ++beginIndex) {
                int endIndex = beginIndex + length - 1;

                if (beginIndex == endIndex) {
                    distances[beginIndex][endIndex] = 0;
                } else if (beginIndex + 1 == endIndex) {
                    distances[beginIndex][endIndex] = houses[endIndex] - houses[beginIndex];
                } else {
                    distances[beginIndex][endIndex] = houses[endIndex] - houses[beginIndex]
                            + distances[beginIndex + 1][endIndex - 1];
                }
            }
        }

        int[][] minTotals = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(minTotals[i], Integer.MAX_VALUE);
        }
        minTotals[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int prevI = 0; prevI < i; ++prevI) {
                    if (minTotals[prevI][j - 1] != Integer.MAX_VALUE) {
                        minTotals[i][j] = Math.min(minTotals[i][j], minTotals[prevI][j - 1] + distances[prevI][i - 1]);
                    }
                }
            }
        }

        return minTotals[n][k];
    }
}