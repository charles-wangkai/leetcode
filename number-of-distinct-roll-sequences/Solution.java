import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int distinctSequences(int n) {
    Map<Integer, Integer> stateToCount =
        IntStream.rangeClosed(1, 6).boxed().collect(Collectors.toMap(i -> i, i -> 1));
    for (int i = 0; i < n - 1; ++i) {
      Map<Integer, Integer> nextStateToCount = new HashMap<>();
      for (int state : stateToCount.keySet()) {
        int prevPrev = state / 7;
        int prev = state % 7;
        for (int j = 1; j <= 6; ++j) {
          if (gcd(j, prev) == 1 && j != prevPrev && j != prev) {
            int nextState = prev * 7 + j;
            nextStateToCount.put(
                nextState,
                addMod(nextStateToCount.getOrDefault(nextState, 0), stateToCount.get(state)));
          }
        }
      }

      stateToCount = nextStateToCount;
    }

    return stateToCount.values().stream().reduce(this::addMod).get();
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}