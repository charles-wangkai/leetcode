import java.util.Arrays;

class Solution {
  public int maxIceCream(int[] costs, int coins) {
    Arrays.sort(costs);

    int result = 0;
    for (int cost : costs) {
      if (coins >= cost) {
        coins -= cost;
        ++result;
      }
    }

    return result;
  }
}
