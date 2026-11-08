import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countSuffix(int[] nums, int[] target) {
    return (Arrays.equals(
            Arrays.stream(nums).sorted().toArray(), Arrays.stream(target).sorted().toArray()))
        ? Math.max(0, findEndIndex(nums, target) - findBeginIndex(target) + 1)
        : 0;
  }

  int findEndIndex(int[] nums, int[] target) {
    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] != target[i])
        .findFirst()
        .orElse(nums.length - 1);
  }

  int findBeginIndex(int[] target) {
    int result = target.length - 1;
    while (result != 0 && target[result - 1] >= target[result]) {
      --result;
    }

    return result;
  }
}