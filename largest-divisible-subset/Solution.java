import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);

    int[] sizes = new int[nums.length];
    int[] prevIndices = new int[nums.length];
    int lastIndex = -1;
    for (int i = 0; i < nums.length; ++i) {
      sizes[i] = 1;
      prevIndices[i] = -1;

      for (int j = 0; j < i; ++j) {
        if (nums[i] % nums[j] == 0 && sizes[j] + 1 > sizes[i]) {
          sizes[i] = sizes[j] + 1;
          prevIndices[i] = j;
        }
      }

      if (lastIndex == -1 || sizes[i] > sizes[lastIndex]) {
        lastIndex = i;
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = lastIndex; i != -1; i = prevIndices[i]) {
      result.add(nums[i]);
    }

    return result;
  }
}
