import java.util.HashMap;
import java.util.Map;

class Solution {
  public int widestPairOfIndices(int[] nums1, int[] nums2) {
    int result = 0;
    Map<Integer, Integer> diffToMinLength = new HashMap<>();
    diffToMinLength.put(0, 0);
    int diff = 0;
    for (int i = 0; i < nums1.length; ++i) {
      diff += nums1[i] - nums2[i];
      if (diffToMinLength.containsKey(diff)) {
        result = Math.max(result, i + 1 - diffToMinLength.get(diff));
      } else {
        diffToMinLength.put(diff, i + 1);
      }
    }

    return result;
  }
}