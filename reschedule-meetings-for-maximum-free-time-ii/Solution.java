import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
    int[] gaps =
        IntStream.concat(
                IntStream.of(startTime[0]),
                IntStream.concat(
                    IntStream.range(0, startTime.length - 1)
                        .map(i -> startTime[i + 1] - endTime[i]),
                    IntStream.of(eventTime - endTime[endTime.length - 1])))
            .toArray();

    SortedMap<Integer, Integer> gapToCount = new TreeMap<>();
    for (int gap : gaps) {
      updateMap(gapToCount, gap, 1);
    }

    int result =
        IntStream.range(0, gaps.length - 1).map(i -> gaps[i] + gaps[i + 1]).max().getAsInt();
    for (int i = 0; i < startTime.length; ++i) {
      updateMap(gapToCount, gaps[i], -1);
      updateMap(gapToCount, gaps[i + 1], -1);

      if (!gapToCount.isEmpty() && gapToCount.lastKey() >= endTime[i] - startTime[i]) {
        result = Math.max(result, gaps[i] + gaps[i + 1] + (endTime[i] - startTime[i]));
      }

      updateMap(gapToCount, gaps[i], 1);
      updateMap(gapToCount, gaps[i + 1], 1);
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> gapToCount, int gap, int delta) {
    gapToCount.put(gap, gapToCount.getOrDefault(gap, 0) + delta);
    gapToCount.remove(gap, 0);
  }
}