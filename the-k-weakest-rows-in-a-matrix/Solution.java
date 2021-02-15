import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] kWeakestRows(int[][] mat, int k) {
    int[] oneCounts = Arrays.stream(mat).mapToInt(row -> Arrays.stream(row).sum()).toArray();

    return IntStream.range(0, mat.length)
        .boxed()
        .sorted(Comparator.comparing((Integer i) -> oneCounts[i]).thenComparing(i -> i))
        .limit(k)
        .mapToInt(x -> x)
        .toArray();
  }
}
