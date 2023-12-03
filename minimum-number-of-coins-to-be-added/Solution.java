import java.util.Arrays;

class Solution {
  public int minimumAddedCoins(int[] coins, int target) {
    Arrays.sort(coins);

    int result = 0;
    int max = 0;
    int index = 0;
    while (max < target) {
      if (index != coins.length && coins[index] <= max + 1) {
        max += coins[index];
        ++index;
      } else {
        max += max + 1;
        ++result;
      }
    }

    return result;
  }
}