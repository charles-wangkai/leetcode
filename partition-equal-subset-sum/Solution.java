import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean canPartition(int[] nums) {
    Set<Integer> dp = Set.of(0);
    for (int num : nums) {
      Set<Integer> nextDp = new HashSet<>(dp);
      for (int prevSum : dp) {
        nextDp.add(prevSum + num);
      }

      dp = nextDp;
    }

    int sum = Arrays.stream(nums).sum();

    return sum % 2 == 0 && dp.contains(sum / 2);
  }
}
