import java.util.stream.IntStream;
import java.util.stream.LongStream;

class Solution {
  public long minimumCost(String s) {
    long[][] leftCosts = buildSideCosts(s);
    long[][] rightCosts = buildSideCosts(new StringBuilder(s).reverse().toString());

    return IntStream.rangeClosed(0, s.length())
        .boxed()
        .flatMapToLong(
            i ->
                LongStream.of(
                    leftCosts[i][0] + rightCosts[s.length() - i][0],
                    leftCosts[i][1] + rightCosts[s.length() - i][1]))
        .min()
        .getAsLong();
  }

  long[][] buildSideCosts(String str) {
    long[][] result = new long[str.length() + 1][2];
    for (int i = 1; i <= str.length(); ++i) {
      if (str.charAt(i - 1) == '0') {
        result[i][0] = Math.min(result[i - 1][0], result[i - 1][1] + i);
        result[i][1] = Math.min(result[i - 1][0] + i, result[i - 1][1] + (i - 1) + i);
      } else {
        result[i][0] = Math.min(result[i - 1][0] + (i - 1) + i, result[i - 1][1] + i);
        result[i][1] = Math.min(result[i - 1][0] + i, result[i - 1][1]);
      }
    }

    return result;
  }
}
