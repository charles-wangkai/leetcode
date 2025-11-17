import java.util.stream.IntStream;

class Solution {
  public boolean kLengthApart(int[] nums, int k) {
    int[] oneIndices = IntStream.range(0, nums.length).filter(i -> nums[i] == 1).toArray();

    return !IntStream.range(0, oneIndices.length - 1)
        .anyMatch(i -> oneIndices[i + 1] - oneIndices[i] < k + 1);
  }
}
