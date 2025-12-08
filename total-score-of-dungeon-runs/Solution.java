import java.util.stream.IntStream;

class Solution {
  public long totalScore(int hp, int[] damage, int[] requirement) {
    int[] prefixSums = new int[damage.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + damage[i];
    }

    return IntStream.range(0, requirement.length)
        .map(i -> i - findBeginIndex(hp, requirement, prefixSums, i) + 1)
        .asLongStream()
        .sum();
  }

  int findBeginIndex(int hp, int[] requirement, int[] prefixSums, int endIndex) {
    int beginIndex = endIndex + 1;
    int lower = 0;
    int upper = endIndex;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (hp - (prefixSums[endIndex] - ((middle == 0) ? 0 : prefixSums[middle - 1]))
          >= requirement[endIndex]) {
        beginIndex = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return beginIndex;
  }
}