import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int subsequencePairCount(int[] nums) {
    Map<State, Integer> stateToWayNum = Map.of(new State(-1, -1), 1);
    for (int num : nums) {
      Map<State, Integer> nextStateToWayNum = new HashMap<>(stateToWayNum);
      for (State state : stateToWayNum.keySet()) {
        for (State nextState :
            new State[] {
              new State((state.g1() == -1) ? num : gcd(state.g1(), num), state.g2()),
              new State(state.g1(), (state.g2() == -1) ? num : gcd(state.g2(), num))
            }) {
          nextStateToWayNum.put(
              nextState,
              addMod(nextStateToWayNum.getOrDefault(nextState, 0), stateToWayNum.get(state)));
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.keySet().stream()
        .filter(state -> state.g1() != -1 && state.g1() == state.g2())
        .mapToInt(stateToWayNum::get)
        .reduce(this::addMod)
        .orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

record State(int g1, int g2) {}
