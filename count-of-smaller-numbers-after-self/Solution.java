import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> countSmaller(int[] nums) {
    int[] smallerNums = new int[nums.length];

    sort(nums, smallerNums, IntStream.range(0, nums.length).toArray(), 0, nums.length - 1);

    return Arrays.stream(smallerNums).boxed().collect(Collectors.toList());
  }

  void sort(int[] nums, int[] smallerNums, int[] indices, int beginIndex, int endIndex) {
    if (beginIndex >= endIndex) {
      return;
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    sort(nums, smallerNums, indices, beginIndex, middleIndex);
    sort(nums, smallerNums, indices, middleIndex + 1, endIndex);

    List<Integer> merged = new ArrayList<>();
    int leftIndex = beginIndex;
    int rightIndex = middleIndex + 1;
    while (leftIndex != middleIndex + 1 || rightIndex != endIndex + 1) {
      if (rightIndex == endIndex + 1
          || (leftIndex != middleIndex + 1
              && nums[indices[leftIndex]] > nums[indices[rightIndex]])) {
        smallerNums[indices[leftIndex]] += endIndex + 1 - rightIndex;

        merged.add(indices[leftIndex]);
        ++leftIndex;
      } else {
        merged.add(indices[rightIndex]);
        ++rightIndex;
      }
    }

    for (int i = beginIndex; i <= endIndex; ++i) {
      indices[i] = merged.get(i - beginIndex);
    }
  }
}
