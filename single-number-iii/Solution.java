import java.util.Arrays;

class Solution {
  public int[] singleNumber(int[] nums) {
    int diffPos =
        Integer.numberOfTrailingZeros(Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt());

    return new int[] {singleNumberForOne(nums, diffPos, 0), singleNumberForOne(nums, diffPos, 1)};
  }

  int singleNumberForOne(int[] nums, int pos, int bit) {
    return Arrays.stream(nums)
        .filter(num -> ((num >> pos) & 1) == bit)
        .reduce((x, y) -> x ^ y)
        .getAsInt();
  }
}
