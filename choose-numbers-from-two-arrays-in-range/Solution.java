import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countSubranges(int[] nums1, int[] nums2) {
    int result = 0;
    Map<Integer, Integer> diffToCount = Map.of();
    for (int i = 0; i < nums1.length; ++i) {
      Map<Integer, Integer> nextDiffToCount = new HashMap<>();
      nextDiffToCount.put(nums1[i], nextDiffToCount.getOrDefault(nums1[i], 0) + 1);
      nextDiffToCount.put(-nums2[i], nextDiffToCount.getOrDefault(-nums2[i], 0) + 1);
      for (int diff : diffToCount.keySet()) {
        nextDiffToCount.put(
            diff + nums1[i],
            addMod(nextDiffToCount.getOrDefault(diff + nums1[i], 0), diffToCount.get(diff)));
        nextDiffToCount.put(
            diff - nums2[i],
            addMod(nextDiffToCount.getOrDefault(diff - nums2[i], 0), diffToCount.get(diff)));
      }

      diffToCount = nextDiffToCount;
      result = addMod(result, diffToCount.getOrDefault(0, 0));
    }

    return result;
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}