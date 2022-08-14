import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int edgeScore(int[] edges) {
    long[] scores = new long[edges.length];
    for (int i = 0; i < edges.length; ++i) {
      scores[edges[i]] += i;
    }

    long maxScore = Arrays.stream(scores).max().getAsLong();

    return IntStream.range(0, scores.length).filter(i -> scores[i] == maxScore).min().getAsInt();
  }
}