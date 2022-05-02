import java.util.HashSet;
import java.util.Set;

class Solution {
  public int countDistinct(int[] nums, int k, int p) {
    Set<String> subarrays = new HashSet<>();
    for (int i = 0; i < nums.length; ++i) {
      int divisibleCount = 0;
      for (int j = i; j < nums.length; ++j) {
        if (nums[j] % p == 0) {
          ++divisibleCount;
        }
        if (divisibleCount == k + 1) {
          break;
        }

        subarrays.add(buildSubarray(nums, i, j));
      }
    }

    return subarrays.size();
  }

  String buildSubarray(int[] nums, int beginIndex, int endIndex) {
    StringBuilder result = new StringBuilder();
    for (int i = beginIndex; i <= endIndex; ++i) {
      if (result.length() != 0) {
        result.append(',');
      }
      result.append(nums[i]);
    }

    return result.toString();
  }
}