import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int bestTeamScore(int[] scores, int[] ages) {
    int[] sortedIndices =
        IntStream.range(0, scores.length)
            .boxed()
            .sorted(
                (i1, i2) ->
                    (ages[i1] != ages[i2])
                        ? Integer.compare(ages[i1], ages[i2])
                        : Integer.compare(scores[i1], scores[i2]))
            .mapToInt(x -> x)
            .toArray();

    int[] sums = new int[scores.length];
    for (int i = 0; i < sums.length; ++i) {
      sums[i] = scores[sortedIndices[i]];
      for (int j = 0; j < i; ++j) {
        if (ages[sortedIndices[j]] == ages[sortedIndices[i]]
            || scores[sortedIndices[j]] <= scores[sortedIndices[i]]) {
          sums[i] = Math.max(sums[i], sums[j] + scores[sortedIndices[i]]);
        }
      }
    }

    return Arrays.stream(sums).max().getAsInt();
  }
}
