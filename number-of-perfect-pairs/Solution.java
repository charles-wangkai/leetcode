import java.util.Arrays;

class Solution {
  public long perfectPairs(int[] nums) {
    int[] sorted = Arrays.stream(nums).map(Math::abs).sorted().toArray();

    long result = 0;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1 && sorted[endIndex + 1] <= 2 * sorted[beginIndex]) {
        ++endIndex;
      }

      result += endIndex - beginIndex;
    }

    return result;
  }
}