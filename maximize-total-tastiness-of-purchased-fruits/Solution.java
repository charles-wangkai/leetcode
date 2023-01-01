import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
    Map<State, Integer> stateToTastiness = Map.of(new State(0, 0), 0);
    for (int i = 0; i < price.length; ++i) {
      Map<State, Integer> nextStateToTastiness = new HashMap<>(stateToTastiness);
      for (State state : stateToTastiness.keySet()) {
        for (State nextState :
            new State[] {
              new State(state.price() + price[i], state.couponCount()),
              new State(state.price() + price[i] / 2, state.couponCount() + 1)
            }) {
          if (nextState.price() <= maxAmount && nextState.couponCount() <= maxCoupons) {
            nextStateToTastiness.put(
                nextState,
                Math.max(
                    nextStateToTastiness.getOrDefault(nextState, 0),
                    stateToTastiness.get(state) + tastiness[i]));
          }
        }
      }

      stateToTastiness = nextStateToTastiness;
    }

    return stateToTastiness.values().stream().mapToInt(x -> x).max().getAsInt();
  }
}

record State(int price, int couponCount) {}
