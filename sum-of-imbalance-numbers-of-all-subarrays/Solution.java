import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int sumImbalanceNumbers(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      int imbalance = 0;
      NavigableSet<Integer> sorted = new TreeSet<>();
      for (int j = i; j < nums.length; ++j) {
        Integer floor = sorted.floor(nums[j]);
        Integer ceiling = sorted.ceiling(nums[j]);

        imbalance +=
            -computeImbalance(floor, ceiling)
                + computeImbalance(floor, nums[j])
                + computeImbalance(nums[j], ceiling);
        result += imbalance;

        sorted.add(nums[j]);
      }
    }

    return result;
  }

  int computeImbalance(Integer x, Integer y) {
    return (x != null && y != null && y - x > 1) ? 1 : 0;
  }
}
