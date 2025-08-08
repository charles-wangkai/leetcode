import java.util.HashMap;
import java.util.Map;

public class Solution {
  Map<State, Double> cache = new HashMap<>();

  public double soupServings(int n) {
    if (n >= 6000) {
      return 1;
    }

    int rest = (n + 24) / 25;

    return search(rest, rest);
  }

  double search(int restA, int restB) {
    if (restA <= 0) {
      return (restB <= 0) ? 0.5 : 1;
    }
    if (restB <= 0) {
      return 0;
    }

    State state = new State(restA, restB);
    if (!cache.containsKey(state)) {
      cache.put(
          state,
          0.25
              * (search(restA - 4, restB)
                  + search(restA - 3, restB - 1)
                  + search(restA - 2, restB - 2)
                  + search(restA - 1, restB - 3)));
    }

    return cache.get(state);
  }
}

record State(int restA, int restB) {}
