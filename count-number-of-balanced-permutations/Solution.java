import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 40;

  int[] factorials;
  int[] factorialInvs;

  public int countBalancedPermutations(String num) {
    precompute();

    int[] counts = new int[10];
    for (char c : num.toCharArray()) {
      ++counts[c - '0'];
    }

    Map<State, Integer> stateToWayNum = Map.of(new State(0, 0, 0), 1);
    for (int i = 0; i < counts.length; ++i) {
      Map<State, Integer> nextStateToWayNum = new HashMap<>();
      for (State state : stateToWayNum.keySet()) {
        for (int even = 0; even <= counts[i]; ++even) {
          int odd = counts[i] - even;
          State nextState =
              new State(
                  state.evenLength() + even,
                  state.oddLength() + odd,
                  state.sumDiff() + i * (even - odd));
          if (nextState.evenLength() <= (num.length() + 1) / 2
              && nextState.oddLength() <= num.length() / 2) {
            nextStateToWayNum.put(
                nextState,
                addMod(
                    nextStateToWayNum.getOrDefault(nextState, 0),
                    multiplyMod(
                        stateToWayNum.get(state),
                        multiplyMod(
                            CMod(nextState.evenLength(), even),
                            CMod(nextState.oddLength(), odd)))));
          }
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.keySet().stream()
        .filter(state -> state.sumDiff() == 0)
        .map(stateToWayNum::get)
        .reduce(this::addMod)
        .orElse(0);
  }

  void precompute() {
    factorials = new int[LIMIT + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    factorialInvs =
        Arrays.stream(factorials)
            .map(x -> BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue())
            .toArray();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int CMod(int n, int r) {
    return multiplyMod(factorials[n], multiplyMod(factorialInvs[r], factorialInvs[n - r]));
  }
}

record State(int evenLength, int oddLength, int sumDiff) {}
