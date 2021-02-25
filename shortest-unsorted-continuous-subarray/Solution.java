import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int findUnsortedSubarray(int[] nums) {
    int[] sorted = Arrays.stream(nums).sorted().toArray();
    int[] diffIndices = IntStream.range(0, nums.length).filter(i -> sorted[i] != nums[i]).toArray();

    return (diffIndices.length == 0)
        ? 0
        : (diffIndices[diffIndices.length - 1] - diffIndices[0] + 1);
  }
}
