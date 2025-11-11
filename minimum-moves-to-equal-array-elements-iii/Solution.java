import java.util.Arrays;

class Solution {
  public int minMoves(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();

    return Arrays.stream(nums).map(x -> max - x).sum();
  }
}