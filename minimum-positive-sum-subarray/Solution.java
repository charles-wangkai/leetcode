import java.util.List;

class Solution {
  public int minimumSumSubarray(List<Integer> nums, int l, int r) {
    int result = Integer.MAX_VALUE;
    for (int beginIndex = 0; beginIndex < nums.size(); ++beginIndex) {
      int sum = 0;
      for (int endIndex = beginIndex; endIndex < nums.size(); ++endIndex) {
        sum += nums.get(endIndex);

        int length = endIndex - beginIndex + 1;
        if (length >= l && length <= r && sum > 0) {
          result = Math.min(result, sum);
        }
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}