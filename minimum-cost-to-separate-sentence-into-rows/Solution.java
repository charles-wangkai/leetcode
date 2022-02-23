import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumCost(String sentence, int k) {
    String[] words = sentence.split(" ");
    long[] dp = new long[words.length + 1];
    Arrays.fill(dp, Long.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < dp.length; ++i) {
      int length = -1;
      for (int j = 1; j <= i; ++j) {
        length += words[i - j].length() + 1;
        if (length > k) {
          break;
        }

        dp[i] = Math.min(dp[i], dp[i - j] + (k - length) * (k - length));
      }
    }

    int lastWordNum = 0;
    int lastLength = -1;
    while (lastWordNum != words.length
        && lastLength + words[words.length - (lastWordNum + 1)].length() + 1 <= k) {
      lastLength += words[words.length - (lastWordNum + 1)].length() + 1;
      ++lastWordNum;
    }

    return (int)
        IntStream.rangeClosed(words.length - lastWordNum, words.length)
            .mapToLong(i -> dp[i])
            .min()
            .getAsLong();
  }
}