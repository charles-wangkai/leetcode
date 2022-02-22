import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[][] averageHeightOfBuildings(int[][] buildings) {
    SortedMap<Integer, Integer> pointToCountDelta = new TreeMap<>();
    SortedMap<Integer, Long> pointToSumDelta = new TreeMap<>();
    for (int[] building : buildings) {
      pointToCountDelta.put(building[0], pointToCountDelta.getOrDefault(building[0], 0) + 1);
      pointToSumDelta.put(building[0], pointToSumDelta.getOrDefault(building[0], 0L) + building[2]);

      pointToCountDelta.put(building[1], pointToCountDelta.getOrDefault(building[1], 0) - 1);
      pointToSumDelta.put(building[1], pointToSumDelta.getOrDefault(building[1], 0L) - building[2]);
    }

    List<int[]> result = new ArrayList<>();
    int count = 0;
    long sum = 0;
    int prevPoint = -1;
    for (int point : pointToCountDelta.keySet()) {
      if (count != 0) {
        int start = prevPoint;
        int end = point;
        int average = (int) (sum / count);

        if (!result.isEmpty()
            && result.get(result.size() - 1)[1] == start
            && result.get(result.size() - 1)[2] == average) {
          result.get(result.size() - 1)[1] = end;
        } else {
          result.add(new int[] {start, end, average});
        }
      }

      count += pointToCountDelta.get(point);
      sum += pointToSumDelta.get(point);
      prevPoint = point;
    }

    return result.toArray(int[][]::new);
  }
}