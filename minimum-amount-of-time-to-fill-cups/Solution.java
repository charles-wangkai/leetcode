import java.util.Arrays;

class Solution {
  public int fillCups(int[] amount) {
    return Math.max(Arrays.stream(amount).max().getAsInt(), (Arrays.stream(amount).sum() + 1) / 2);
  }
}