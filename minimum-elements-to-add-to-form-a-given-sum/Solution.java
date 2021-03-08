import java.util.Arrays;

class Solution {
  public int minElements(int[] nums, int limit, int goal) {
    return (int) ((Math.abs(Arrays.stream(nums).asLongStream().sum() - goal) + limit - 1) / limit);
  }
}
