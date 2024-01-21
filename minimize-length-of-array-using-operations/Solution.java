import java.util.Arrays;

class Solution {
  public int minimumArrayLength(int[] nums) {
    int min = Arrays.stream(nums).min().getAsInt();

    return Arrays.stream(nums).anyMatch(x -> x % min != 0)
        ? 1
        : (((int) Arrays.stream(nums).filter(x -> x == min).count() + 1) / 2);
  }
}