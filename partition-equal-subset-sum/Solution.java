import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();

    Set<Integer> partialSums = new HashSet<>();
    partialSums.add(0);

    for (int num : nums) {
      Set<Integer> nextPartialSums = new HashSet<>();
      nextPartialSums.addAll(partialSums);

      for (int partialSum : partialSums) {
        nextPartialSums.add(partialSum + num);
      }

      partialSums = nextPartialSums;
    }

    return sum % 2 == 0 && partialSums.contains(sum / 2);
  }
}
