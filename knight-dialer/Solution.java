import java.util.Arrays;

public class Solution {
  static final int MOD_DIVISOR = 1000000007;

  public int knightDialer(int N) {
    int[] ways = new int[10];
    Arrays.fill(ways, 1);

    for (int i = 0; i < N - 1; i++) {
      int[] nextWays = new int[10];
      nextWays[0] = addMod(ways[4], ways[6]);
      nextWays[1] = addMod(ways[6], ways[8]);
      nextWays[2] = addMod(ways[7], ways[9]);
      nextWays[3] = addMod(ways[4], ways[8]);
      nextWays[4] = addMod(addMod(ways[0], ways[3]), ways[9]);
      nextWays[5] = 0;
      nextWays[6] = addMod(addMod(ways[0], ways[1]), ways[7]);
      nextWays[7] = addMod(ways[2], ways[6]);
      nextWays[8] = addMod(ways[1], ways[3]);
      nextWays[9] = addMod(ways[2], ways[4]);

      ways = nextWays;
    }

    return Arrays.stream(ways).reduce(0, this::addMod);
  }

  int addMod(int x, int y) {
    return (x + y) % MOD_DIVISOR;
  }
}
