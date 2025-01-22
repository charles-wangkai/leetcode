import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] arr, int[] brr, long k) {
    return Math.min(
        computeDiffSum(arr, brr),
        k
            + computeDiffSum(
                Arrays.stream(arr).sorted().toArray(), Arrays.stream(brr).sorted().toArray()));
  }

  long computeDiffSum(int[] x, int[] y) {
    return IntStream.range(0, x.length).map(i -> Math.abs(x[i] - y[i])).asLongStream().sum();
  }
}