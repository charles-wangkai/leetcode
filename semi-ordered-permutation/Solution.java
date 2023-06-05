import java.util.stream.IntStream;

class Solution {
  public int semiOrderedPermutation(int[] nums) {
    int smallIndex = IntStream.range(0, nums.length).filter(i -> nums[i] == 1).findAny().getAsInt();
    int largeIndex =
        IntStream.range(0, nums.length).filter(i -> nums[i] == nums.length).findAny().getAsInt();

    return smallIndex + (nums.length - 1 - largeIndex) - ((smallIndex > largeIndex) ? 1 : 0);
  }
}
