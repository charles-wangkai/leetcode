import java.util.Arrays;

class Solution {
  public int maxIceCream(int[] costs, int coins) {
    int[] sortedCosts = Arrays.stream(costs).boxed().sorted().mapToInt(x -> x).toArray();

    int result = 0;
    for (int cost : sortedCosts) {
      if (coins >= cost) {
        coins -= cost;
        ++result;
      }
    }

    return result;
  }
}
