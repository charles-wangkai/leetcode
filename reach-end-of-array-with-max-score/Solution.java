import java.util.List;

class Solution {
  public long findMaximumScore(List<Integer> nums) {
    long result = 0;
    int max = 0;
    for (int i = 0; i < nums.size() - 1; ++i) {
      max = Math.max(max, nums.get(i));
      result += max;
    }

    return result;
  }
}