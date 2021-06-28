import java.util.Arrays;

class Solution {
  public int maxProductDifference(int[] nums) {
    int[] sorted = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    return sorted[sorted.length - 1] * sorted[sorted.length - 2] - sorted[0] * sorted[1];
  }
}
