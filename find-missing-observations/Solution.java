import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] missingRolls(int[] rolls, int mean, int n) {
    int total = mean * (rolls.length + n) - Arrays.stream(rolls).sum();

    return (total >= n && total <= n * 6)
        ? IntStream.range(0, n).map(i -> total / n + ((i < total % n) ? 1 : 0)).toArray()
        : new int[0];
  }
}
