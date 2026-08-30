import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumDeletions(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }

    int indexForMinValue =
        IntStream.range(0, nums.length).boxed().min(Comparator.comparing(i -> nums[i])).get();
    int indexForMaxValue =
        IntStream.range(0, nums.length).boxed().max(Comparator.comparing(i -> nums[i])).get();

    int leftIndex = Math.min(indexForMinValue, indexForMaxValue);
    int rightIndex = Math.max(indexForMinValue, indexForMaxValue);

    return Math.min(
        Math.min(rightIndex + 1, nums.length - leftIndex),
        (leftIndex + 1) + (nums.length - rightIndex));
  }
}