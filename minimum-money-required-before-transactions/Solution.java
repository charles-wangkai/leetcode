import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long minimumMoney(int[][] transactions) {
    int[] lossIndices =
        IntStream.range(0, transactions.length)
            .filter(i -> transactions[i][0] > transactions[i][1])
            .toArray();
    int[] gainIndices =
        IntStream.range(0, transactions.length)
            .filter(i -> transactions[i][0] <= transactions[i][1])
            .toArray();

    long lossSum =
        Arrays.stream(lossIndices)
            .map(i -> transactions[i][0] - transactions[i][1])
            .asLongStream()
            .sum();

    return Math.max(
        lossSum + Arrays.stream(gainIndices).map(i -> transactions[i][0]).max().orElse(0),
        lossSum + Arrays.stream(lossIndices).map(i -> transactions[i][1]).max().orElse(0));
  }
}