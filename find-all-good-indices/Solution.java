import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> goodIndices(int[] nums, int k) {
    int[] leftLengths = new int[nums.length];
    for (int i = 0; i < leftLengths.length; ++i) {
      leftLengths[i] = (i != 0 && nums[i] <= nums[i - 1]) ? (leftLengths[i - 1] + 1) : 1;
    }

    int[] rightLengths = new int[nums.length];
    for (int i = rightLengths.length - 1; i >= 0; --i) {
      rightLengths[i] =
          (i != rightLengths.length - 1 && nums[i] <= nums[i + 1]) ? (rightLengths[i + 1] + 1) : 1;
    }

    return IntStream.range(k, nums.length - k)
        .filter(i -> leftLengths[i - 1] >= k && rightLengths[i + 1] >= k)
        .boxed()
        .toList();
  }
}