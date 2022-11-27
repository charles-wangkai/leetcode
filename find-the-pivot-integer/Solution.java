import java.util.stream.IntStream;

class Solution {
  public int pivotInteger(int n) {
    int[] sums = IntStream.rangeClosed(0, n).map(i -> i * (i + 1) / 2).toArray();

    return IntStream.rangeClosed(1, n)
        .filter(i -> sums[i] == sums[n] - sums[i - 1])
        .findAny()
        .orElse(-1);
  }
}
