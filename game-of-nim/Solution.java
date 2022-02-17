import java.util.Arrays;

class Solution {
  public boolean nimGame(int[] piles) {
    return Arrays.stream(piles).reduce((x, y) -> x ^ y).getAsInt() != 0;
  }
}