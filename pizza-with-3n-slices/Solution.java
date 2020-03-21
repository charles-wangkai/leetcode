import java.util.Arrays;

class Solution {
    public int maxSizeSlices(int[] slices) {
        int length = slices.length;

        return Math.max(slices[0] + computeMaxSum(Arrays.copyOfRange(slices, 2, length - 1), length / 3 - 1),
                computeMaxSum(Arrays.copyOfRange(slices, 1, length), length / 3));
    }

    int computeMaxSum(int[] values, int pickNum) {
        int length = values.length;

        int[][] maxSums = new int[length + 1][pickNum + 1];
        for (int i = 0; i < maxSums.length; ++i) {
            Arrays.fill(maxSums[i], -1);
        }
        maxSums[0][0] = 0;

        for (int i = 1; i < maxSums.length; ++i) {
            for (int j = 0; j <= pickNum; ++j) {
                maxSums[i][j] = maxSums[i - 1][j];

                if (j == 1) {
                    maxSums[i][j] = Math.max(maxSums[i][j], values[i - 1]);
                }

                if (i >= 2 && j >= 1) {
                    maxSums[i][j] = Math.max(maxSums[i][j], maxSums[i - 2][j - 1] + values[i - 1]);
                }
            }
        }

        return maxSums[length][pickNum];
    }
}