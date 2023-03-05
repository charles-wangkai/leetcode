import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findValidSplit(int[] nums) {
    Map<Integer, Range> primeToRanges = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int rest = nums[i];
      for (int j = 2; j * j <= rest; ++j) {
        if (rest % j == 0) {
          update(primeToRanges, j, i);

          while (rest % j == 0) {
            rest /= j;
          }
        }
      }
      if (rest != 1) {
        update(primeToRanges, rest, i);
      }
    }

    Range[] sortedRanges =
        primeToRanges.values().stream()
            .sorted(Comparator.comparing(range -> range.beginIndex))
            .toArray(Range[]::new);
    int rangeIndex = 0;
    int maxEnd = -1;
    for (int i = 0; i < nums.length - 1; ++i) {
      while (rangeIndex != sortedRanges.length && sortedRanges[rangeIndex].beginIndex == i) {
        maxEnd = Math.max(maxEnd, sortedRanges[rangeIndex].endIndex - 1);
        ++rangeIndex;
      }

      if (i > maxEnd) {
        return i;
      }
    }

    return -1;
  }

  void update(Map<Integer, Range> primeToRanges, int prime, int index) {
    primeToRanges.putIfAbsent(prime, new Range(index));
    primeToRanges.get(prime).endIndex = index;
  }
}

class Range {
  int beginIndex;
  int endIndex;

  public Range(int beginIndex) {
    this.beginIndex = beginIndex;
  }
}
