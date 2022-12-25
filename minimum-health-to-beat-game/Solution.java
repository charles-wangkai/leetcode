import java.util.Arrays;

class Solution {
  public long minimumHealth(int[] damage, int armor) {
    return Arrays.stream(damage).asLongStream().sum()
        - Math.min(armor, Arrays.stream(damage).max().getAsInt())
        + 1;
  }
}
