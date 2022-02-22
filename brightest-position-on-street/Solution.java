import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int brightestPosition(int[][] lights) {
    SortedMap<Integer, Integer> pointToDelta = new TreeMap<>();
    for (int[] light : lights) {
      pointToDelta.put(light[0] - light[1], pointToDelta.getOrDefault(light[0] - light[1], 0) + 1);
      pointToDelta.put(
          light[0] + light[1] + 1, pointToDelta.getOrDefault(light[0] + light[1] + 1, 0) - 1);
    }

    int maxLampNum = -1;
    int result = Integer.MIN_VALUE;
    int lampNum = 0;
    for (int point : pointToDelta.keySet()) {
      lampNum += pointToDelta.get(point);
      if (lampNum > maxLampNum) {
        maxLampNum = lampNum;
        result = point;
      }
    }

    return result;
  }
}