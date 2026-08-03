import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minMaxWaitingTime(int[] demand, int[] fuel) {
    int result = -1;
    Map<State, Integer> dp = Map.of(new State(fuel[0], fuel[1], 0, 0), 0);
    for (int d : demand) {
      Map<State, Integer> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        if (d <= state.rest0()) {
          int waitTime = state.occupied0();
          State nexState =
              new State(
                  state.rest0() - d, state.rest1(), d, Math.max(0, state.occupied1() - waitTime));
          nextDp.put(
              nexState,
              Math.min(
                  nextDp.getOrDefault(nexState, Integer.MAX_VALUE),
                  Math.max(dp.get(state), waitTime)));
        }

        if (d <= state.rest1()) {
          int waitTime = state.occupied1();
          State nexState =
              new State(
                  state.rest0(), state.rest1() - d, Math.max(0, state.occupied0() - waitTime), d);
          nextDp.put(
              nexState,
              Math.min(
                  nextDp.getOrDefault(nexState, Integer.MAX_VALUE),
                  Math.max(dp.get(state), waitTime)));
        }
      }

      if (nextDp.isEmpty()) {
        break;
      }

      result = nextDp.values().stream().mapToInt(Integer::intValue).min().getAsInt();
      dp = nextDp;
    }

    return result;
  }
}

record State(int rest0, int rest1, int occupied0, int occupied1) {}
