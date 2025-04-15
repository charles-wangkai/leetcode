import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countNumbers(String l, String r, int b) {
    return addMod(
        computeWayNum(b, new BigInteger(r).add(BigInteger.ONE).toString()), -computeWayNum(b, l));
  }

  int computeWayNum(int b, String limit) {
    int[] digits = new BigInteger(limit).toString(b).chars().map(c -> c - '0').toArray();

    Map<State, Integer> dp = Map.of(new State(0, true), 1);
    for (int digit : digits) {
      Map<State, Integer> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (int d = state.lastDigit(); d <= (state.tight() ? digit : (b - 1)); ++d) {
          State nextState = new State(d, state.tight() && d == digit);
          nextDp.put(nextState, addMod(nextDp.getOrDefault(nextState, 0), dp.get(state)));
        }
      }

      dp = nextDp;
    }

    return dp.keySet().stream()
        .filter(state -> !state.tight())
        .map(dp::get)
        .reduce(this::addMod)
        .orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record State(int lastDigit, boolean tight) {}
