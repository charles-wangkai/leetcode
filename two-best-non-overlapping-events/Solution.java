import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int maxTwoEvents(int[][] events) {
    Arrays.sort(events, Comparator.comparing(event -> event[0]));

    NavigableMap<Integer, Integer> startTimeToMaxAfterValue = new TreeMap<>();
    int maxAfterValue = 0;
    for (int i = events.length - 1; i >= 0; --i) {
      maxAfterValue = Math.max(maxAfterValue, events[i][2]);
      startTimeToMaxAfterValue.put(events[i][0], maxAfterValue);
    }

    int result = 0;
    for (int[] event : events) {
      result = Math.max(result, event[2]);

      Entry<Integer, Integer> entry = startTimeToMaxAfterValue.higherEntry(event[1]);
      if (entry != null) {
        result = Math.max(result, event[2] + entry.getValue());
      }
    }

    return result;
  }
}
