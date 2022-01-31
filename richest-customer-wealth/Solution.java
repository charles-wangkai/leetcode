import java.util.Arrays;

class Solution {
  public int maximumWealth(int[][] accounts) {
    return Arrays.stream(accounts)
        .map(a -> Arrays.stream(a).sum())
        .mapToInt(x -> x)
        .max()
        .getAsInt();
  }
}
