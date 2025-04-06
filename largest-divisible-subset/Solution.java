import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);

    int[] sizes = new int[nums.length];
    int[] prevIndices = new int[nums.length];
    int lastIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      sizes[i] = 1;
      prevIndices[i] = -1;

      for (int j = 0; j < i; ++j) {
        if (nums[i] % nums[j] == 0 && sizes[j] + 1 > sizes[i]) {
          sizes[i] = sizes[j] + 1;
          prevIndices[i] = j;
        }
      }

      if (sizes[i] > sizes[lastIndex]) {
        lastIndex = i;
      }
    }

    return IntStream.iterate(lastIndex, i -> i != -1, i -> prevIndices[i])
        .map(i -> nums[i])
        .boxed()
        .toList();
  }
}
