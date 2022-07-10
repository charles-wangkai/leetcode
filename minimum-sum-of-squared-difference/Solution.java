import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
    SortedMap<Integer, Integer> diffToCount = new TreeMap<>();
    for (int i = 0; i < nums1.length; ++i) {
      updateMap(diffToCount, Math.abs(nums1[i] - nums2[i]), 1);
    }

    int rest = k1 + k2;
    while (!diffToCount.isEmpty()) {
      int maxDiff = diffToCount.lastKey();
      int count = diffToCount.get(maxDiff);
      if (rest < count) {
        updateMap(diffToCount, maxDiff, -rest);
        updateMap(diffToCount, maxDiff - 1, rest);

        break;
      }

      diffToCount.remove(maxDiff);
      int amount =
          Math.min(
              diffToCount.isEmpty() ? Integer.MAX_VALUE : (maxDiff - diffToCount.lastKey()),
              rest / count);
      updateMap(diffToCount, maxDiff - amount, count);
      rest -= amount * count;
    }

    return diffToCount.keySet().stream()
        .mapToLong(diff -> (long) diff * diff * diffToCount.get(diff))
        .sum();
  }

  void updateMap(SortedMap<Integer, Integer> diffToCount, int diff, int delta) {
    if (diff >= 1) {
      diffToCount.put(diff, diffToCount.getOrDefault(diff, 0) + delta);
      diffToCount.remove(diff, 0);
    }
  }
}