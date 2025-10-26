import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countCoprime(int[][] mat) {
    Map<Integer, Integer> dp = Map.of(-1, 1);
    for (int[] line : mat) {
      Map<Integer, Integer> nextDp = new HashMap<>();
      for (int g : dp.keySet()) {
        for (int value : line) {
          int nextG = (g == -1) ? value : gcd(g, value);

          nextDp.put(nextG, addMod(nextDp.getOrDefault(nextG, 0), dp.get(g)));
        }
      }

      dp = nextDp;
    }

    return dp.getOrDefault(1, 0);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}