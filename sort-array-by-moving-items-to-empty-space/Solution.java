import java.util.stream.IntStream;

class Solution {
  public int sortArray(int[] nums) {
    return Math.min(
        computeOperationNum(nums, IntStream.range(0, nums.length).toArray()),
        computeOperationNum(
            nums, IntStream.range(0, nums.length).map(i -> (i + 1) % nums.length).toArray()));
  }

  int computeOperationNum(int[] nums, int[] target) {
    int result = 0;
    int[] valueToIndex = new int[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      valueToIndex[nums[i]] = i;
    }
    int firstDiffValue = 1;
    while (true) {
      int value;
      if (target[valueToIndex[0]] == 0) {
        while (firstDiffValue != nums.length
            && target[valueToIndex[firstDiffValue]] == firstDiffValue) {
          ++firstDiffValue;
        }
        if (firstDiffValue == nums.length) {
          return result;
        }

        value = firstDiffValue;
      } else {
        value = target[valueToIndex[0]];
      }

      int index1 = valueToIndex[0];
      int index2 = valueToIndex[value];
      valueToIndex[0] = index2;
      valueToIndex[value] = index1;

      ++result;
    }
  }
}
