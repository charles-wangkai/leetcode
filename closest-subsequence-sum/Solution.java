import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int minAbsDifference(int[] nums, int goal) {
    NavigableSet<Integer> sums1 =
        buildSums(IntStream.range(0, nums.length / 2).map(i -> nums[i]).toArray());

    return buildSums(IntStream.range(nums.length / 2, nums.length).map(i -> nums[i]).toArray())
        .stream()
        .mapToInt(
            sum2 -> {
              int target = goal - sum2;
              int minDiff = Integer.MAX_VALUE;

              Integer floor = sums1.floor(target);
              if (floor != null) {
                minDiff = Math.min(minDiff, target - floor);
              }

              Integer ceiling = sums1.ceiling(target);
              if (ceiling != null) {
                minDiff = Math.min(minDiff, ceiling - target);
              }

              return minDiff;
            })
        .min()
        .getAsInt();
  }

  NavigableSet<Integer> buildSums(int[] a) {
    NavigableSet<Integer> sums = new TreeSet<>();
    for (int code = 0; code < 1 << a.length; ++code) {
      int sum = 0;
      for (int i = 0; i < a.length; ++i) {
        if ((code & (1 << i)) != 0) {
          sum += a[i];
        }
      }

      sums.add(sum);
    }

    return sums;
  }
}
