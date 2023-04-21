import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
    Map<State, Integer> stateToSchemeNum = new HashMap<>();
    stateToSchemeNum.put(new State(0, 0), 1);
    for (int i = 0; i < group.length; ++i) {
      Map<State, Integer> nextStateToSchemeNum = new HashMap<>(stateToSchemeNum);

      for (State state : stateToSchemeNum.keySet()) {
        State nextState =
            new State(state.group() + group[i], Math.min(minProfit, state.profit() + profit[i]));
        if (nextState.group() <= n) {
          nextStateToSchemeNum.put(
              nextState,
              addMod(nextStateToSchemeNum.getOrDefault(nextState, 0), stateToSchemeNum.get(state)));
        }
      }

      stateToSchemeNum = nextStateToSchemeNum;
    }

    return stateToSchemeNum.keySet().stream()
        .filter(state -> state.profit() >= minProfit)
        .map(stateToSchemeNum::get)
        .reduce(this::addMod)
        .orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record State(int group, int profit) {}
