import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int bestTeamScore(int[] scores, int[] ages) {
    int[] sortedIndices =
        IntStream.range(0, scores.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> ages[i]).thenComparing(i -> scores[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] dp = new int[scores.length];
    for (int i = 0; i < dp.length; ++i) {
      dp[i] = scores[sortedIndices[i]];
      for (int j = 0; j < i; ++j) {
        if (ages[sortedIndices[j]] == ages[sortedIndices[i]]
            || scores[sortedIndices[j]] <= scores[sortedIndices[i]]) {
          dp[i] = Math.max(dp[i], dp[j] + scores[sortedIndices[i]]);
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}
