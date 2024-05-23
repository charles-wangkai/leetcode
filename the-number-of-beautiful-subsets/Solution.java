import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1000;

  public int beautifulSubsets(int[] nums, int k) {
    return (int)
        IntStream.range(1, 1 << nums.length)
            .filter(
                mask -> {
                  Set<Integer> seen = new HashSet<>();
                  for (int i = 0; i < nums.length; ++i) {
                    if (((mask >> i) & 1) == 1) {
                      if (seen.contains(nums[i] - k) || seen.contains(nums[i] + k)) {
                        return false;
                      }

                      seen.add(nums[i]);
                    }
                  }

                  return true;
                })
            .count();
  }
}
