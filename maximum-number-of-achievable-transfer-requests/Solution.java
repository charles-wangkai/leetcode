import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumRequests(int n, int[][] requests) {
    return IntStream.range(0, 1 << requests.length)
        .filter(
            mask -> {
              int[] deltas = new int[n];
              for (int i = 0; i < requests.length; ++i) {
                if (((mask >> i) & 1) == 1) {
                  --deltas[requests[i][0]];
                  ++deltas[requests[i][1]];
                }
              }

              return Arrays.stream(deltas).allMatch(delta -> delta == 0);
            })
        .map(Integer::bitCount)
        .max()
        .getAsInt();
  }
}
