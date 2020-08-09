import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int minCost(int n, int[] cuts) {
        int[] sorted = IntStream.concat(IntStream.of(0, n), Arrays.stream(cuts)).sorted().toArray();

        int[][] costs = new int[sorted.length][sorted.length];
        for (int length = 2; length <= sorted.length - 1; ++length) {
            for (int beginIndex = 0; beginIndex + length < sorted.length; ++beginIndex) {
                int endIndex = beginIndex + length;

                int beginIndex_ = beginIndex;
                costs[beginIndex][endIndex] = sorted[endIndex] - sorted[beginIndex]
                        + IntStream.range(beginIndex + 1, endIndex).map(i -> costs[beginIndex_][i] + costs[i][endIndex])
                                .min().getAsInt();
            }
        }

        return costs[0][sorted.length - 1];
    }
}