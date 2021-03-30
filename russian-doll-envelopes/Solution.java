import java.util.Arrays;
import java.util.Comparator;

public class Solution {
  public int maxEnvelopes(int[][] envelopes) {
    Arrays.sort(envelopes, Comparator.comparing(e -> e[0]));

    int[] maxLengths = new int[envelopes.length];
    for (int i = 0; i < maxLengths.length; ++i) {
      maxLengths[i] = 1;
      for (int j = 0; j < i; ++j) {
        if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
          maxLengths[i] = Math.max(maxLengths[i], maxLengths[j] + 1);
        }
      }
    }

    return Arrays.stream(maxLengths).max().getAsInt();
  }
}
