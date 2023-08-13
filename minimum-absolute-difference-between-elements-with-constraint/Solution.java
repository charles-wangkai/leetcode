import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int minAbsoluteDifference(List<Integer> nums, int x) {
    int result = Integer.MAX_VALUE;
    NavigableSet<Integer> seen = new TreeSet<>();
    for (int i = x; i < nums.size(); ++i) {
      seen.add(nums.get(i - x));

      Integer floor = seen.floor(nums.get(i));
      if (floor != null) {
        result = Math.min(result, nums.get(i) - floor);
      }

      Integer ceiling = seen.ceiling(nums.get(i));
      if (ceiling != null) {
        result = Math.min(result, ceiling - nums.get(i));
      }
    }

    return result;
  }
}
