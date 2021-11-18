import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findDisappearedNumbers(int[] nums) {
    for (int num : nums) {
      int index = Math.abs(num) - 1;
      nums[index] = -Math.abs(nums[index]);
    }

    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] >= 1)
        .map(i -> i + 1)
        .boxed()
        .collect(Collectors.toList());
  }
}
