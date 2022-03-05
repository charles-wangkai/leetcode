import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int deleteAndEarn(int[] nums) {
    SortedMap<Integer, Integer> numToCount = new TreeMap<>();
    for (int num : nums) {
      numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
    }

    int maxPoint = 0;
    int prevPrevPoint = 0;
    int prevPoint = 0;
    int prevNum = 0;
    for (Entry<Integer, Integer> entry : numToCount.entrySet()) {
      int num = entry.getKey();
      int count = entry.getValue();

      int currPoint =
          Math.max(num * count + ((num == prevNum + 1) ? prevPrevPoint : prevPoint), prevPoint);
      maxPoint = Math.max(maxPoint, currPoint);

      prevPrevPoint = prevPoint;
      prevPoint = currPoint;
      prevNum = num;
    }

    return maxPoint;
  }
}
