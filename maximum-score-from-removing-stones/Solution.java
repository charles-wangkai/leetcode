import java.util.stream.IntStream;

class Solution {
  public int maximumScore(int a, int b, int c) {
    int[] values = IntStream.of(a, b, c).sorted().toArray();

    if (values[0] + values[1] <= values[2]) {
      return values[0] + values[1];
    }

    return values[2] + (values[0] + values[1] - values[2]) / 2;
  }
}
