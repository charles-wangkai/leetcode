import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean isCovered(int[][] ranges, int left, int right) {
    return IntStream.rangeClosed(left, right)
        .allMatch(x -> Arrays.stream(ranges).anyMatch(range -> x >= range[0] && x <= range[1]));
  }
}
