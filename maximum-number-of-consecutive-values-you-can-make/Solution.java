import java.util.Arrays;

class Solution {
  public int getMaximumConsecutive(int[] coins) {
    coins = Arrays.stream(coins).boxed().sorted().mapToInt(x -> x).toArray();

    int max = 0;
    for (int coin : coins) {
      if (coin > max + 1) {
        break;
      }

      max += coin;
    }

    return max + 1;
  }
}
