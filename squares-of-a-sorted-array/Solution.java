import java.util.Arrays;

class Solution {
  public int[] sortedSquares(int[] nums) {
    return Arrays.stream(nums).map(x -> x * x).sorted().toArray();
  }
}
