import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int minTime(String s, int[] order, int k) {
    NavigableSet<Range> ranges = new TreeSet<>(Comparator.comparing(Range::beginIndex));
    ranges.add(new Range(0, s.length() - 1));
    long validNum = 0;

    for (int i = 0; i < order.length; ++i) {
      Range range = ranges.floor(new Range(order[i], order[i]));

      ranges.remove(range);

      if (order[i] != range.beginIndex()) {
        ranges.add(new Range(range.beginIndex(), order[i] - 1));
      }
      if (order[i] != range.endIndex()) {
        ranges.add(new Range(order[i] + 1, range.endIndex()));
      }

      validNum +=
          computeSubstringNum(range.endIndex() - range.beginIndex() + 1)
              - computeSubstringNum(order[i] - range.beginIndex())
              - computeSubstringNum(range.endIndex() - order[i]);
      if (validNum >= k) {
        return i;
      }
    }

    return -1;
  }

  long computeSubstringNum(int length) {
    return length * (length + 1L) / 2;
  }
}

record Range(int beginIndex, int endIndex) {}
