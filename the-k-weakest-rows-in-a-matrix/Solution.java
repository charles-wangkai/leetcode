import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.IntStream;

class Solution {
  public int[] kWeakestRows(int[][] mat, int k) {
    int[] oneCounts = Arrays.stream(mat).mapToInt(row -> Arrays.stream(row).sum()).toArray();

    return IntStream.range(0, mat.length)
        .boxed()
        .sorted(
            Comparator.<Integer, Integer>comparing(i -> oneCounts[i])
                .thenComparing(Function.identity()))
        .limit(k)
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
