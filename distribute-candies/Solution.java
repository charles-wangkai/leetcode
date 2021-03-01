import java.util.Arrays;

class Solution {
  public int distributeCandies(int[] candies) {
    return Math.min(candies.length / 2, (int) Arrays.stream(candies).distinct().count());
  }
}
