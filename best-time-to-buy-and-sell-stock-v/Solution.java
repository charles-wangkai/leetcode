import java.util.HashMap;
import java.util.Map;

class Solution {
  public long maximumProfit(int[] prices, int k) {
    Map<State, Long> stateToProfit = Map.of(new State(0, 0), 0L);
    for (int price : prices) {
      Map<State, Long> nextStateToProfit = new HashMap<>();
      for (State state : stateToProfit.keySet()) {
        updateMap(nextStateToProfit, state, stateToProfit.get(state));

        if (state.status() == -1) {
          updateMap(
              nextStateToProfit,
              new State(state.transactionCount() + 1, 0),
              stateToProfit.get(state) + price);
        } else if (state.status() == 1) {
          updateMap(
              nextStateToProfit,
              new State(state.transactionCount() + 1, 0),
              stateToProfit.get(state) - price);
        } else if (state.transactionCount() < k) {
          updateMap(
              nextStateToProfit,
              new State(state.transactionCount(), -1),
              stateToProfit.get(state) - price);
          updateMap(
              nextStateToProfit,
              new State(state.transactionCount(), 1),
              stateToProfit.get(state) + price);
        }
      }

      stateToProfit = nextStateToProfit;
    }

    return stateToProfit.keySet().stream()
        .filter(state -> state.status() == 0)
        .map(stateToProfit::get)
        .mapToLong(Long::longValue)
        .max()
        .getAsLong();
  }

  void updateMap(Map<State, Long> stateToProfit, State state, long profit) {
    stateToProfit.put(state, Math.max(stateToProfit.getOrDefault(state, Long.MIN_VALUE), profit));
  }
}

record State(int transactionCount, int status) {}
