import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maximumCost(int n, int[][] highways, int k) {
    Map<State, Integer> stateToCost =
        IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> new State(1 << i, i), i -> 0));
    for (int i = 0; i < k; ++i) {
      Map<State, Integer> nextStateToCost = new HashMap<>();
      for (State state : stateToCost.keySet()) {
        for (int[] highway : highways) {
          int nextCity =
              (highway[0] == state.lastCity())
                  ? highway[1]
                  : ((highway[1] == state.lastCity()) ? highway[0] : -1);
          if (nextCity != -1 && ((state.mask() >> nextCity) & 1) == 0) {
            State nextState = new State(state.mask() + (1 << nextCity), nextCity);
            nextStateToCost.put(
                nextState,
                Math.max(
                    nextStateToCost.getOrDefault(nextState, 0),
                    stateToCost.get(state) + highway[2]));
          }
        }
      }

      stateToCost = nextStateToCost;
    }

    return stateToCost.values().stream().mapToInt(x -> x).max().orElse(-1);
  }
}

record State(int mask, int lastCity) {}
