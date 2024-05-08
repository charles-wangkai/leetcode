import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String[] findRelativeRanks(int[] score) {
    int[] sortedIndices =
        IntStream.range(0, score.length)
            .boxed()
            .sorted(Comparator.<Integer, Integer>comparing(i -> score[i]).reversed())
            .mapToInt(Integer::intValue)
            .toArray();

    String[] result = new String[score.length];
    for (int i = 0; i < sortedIndices.length; ++i) {
      result[sortedIndices[i]] = toRank(i);
    }

    return result;
  }

  String toRank(int pos) {
    if (pos == 0) {
      return "Gold Medal";
    }
    if (pos == 1) {
      return "Silver Medal";
    }
    if (pos == 2) {
      return "Bronze Medal";
    }

    return String.valueOf(pos + 1);
  }
}
