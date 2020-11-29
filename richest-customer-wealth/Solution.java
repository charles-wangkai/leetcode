import java.util.Arrays;

class Solution {
  public int maximumWealth(int[][] accounts) {
    return Arrays.stream(accounts)
        .map(account -> Arrays.stream(account).sum())
        .mapToInt(x -> x)
        .max()
        .getAsInt();
  }
}
