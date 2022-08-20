import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
    int n = nums.length;

    long[] result = new long[n];
    SortedSet<Segment> segments = new TreeSet<>(Comparator.comparing(s -> s.sum()));
    Map<Integer, Segment> beginIndexToSegment = new HashMap<>();
    Map<Integer, Segment> endIndexToSegment = new HashMap<>();
    for (int i = result.length - 2; i >= 0; --i) {
      int beginIndex = removeQueries[i + 1];
      int endIndex = beginIndex;
      long sum = nums[beginIndex];

      if (endIndexToSegment.containsKey(beginIndex - 1)) {
        Segment leftSegment = endIndexToSegment.get(beginIndex - 1);
        segments.remove(leftSegment);
        beginIndexToSegment.remove(leftSegment.beginIndex());
        endIndexToSegment.remove(leftSegment.endIndex());

        beginIndex = leftSegment.beginIndex();
        sum += leftSegment.sum();
      }
      if (beginIndexToSegment.containsKey(endIndex + 1)) {
        Segment rightSegment = beginIndexToSegment.get(endIndex + 1);
        segments.remove(rightSegment);
        beginIndexToSegment.remove(rightSegment.beginIndex());
        endIndexToSegment.remove(rightSegment.endIndex());

        endIndex = rightSegment.endIndex();
        sum += rightSegment.sum();
      }

      Segment segment = new Segment(beginIndex, endIndex, sum);
      segments.add(segment);
      beginIndexToSegment.put(beginIndex, segment);
      endIndexToSegment.put(endIndex, segment);

      result[i] = segments.last().sum();
    }

    return result;
  }
}

record Segment(int beginIndex, int endIndex, long sum) {}