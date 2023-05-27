import java.util.HashMap;
import java.util.Map;

class Solution {
  Map<State, Score> cache;

  public String stoneGameIII(int[] stoneValue) {
    cache = new HashMap<>();
    Score score = search(stoneValue, 0, true);

    if (score.alice() > score.bob()) {
      return "Alice";
    }
    if (score.alice() < score.bob()) {
      return "Bob";
    }

    return "Tie";
  }

  Score search(int[] stoneValue, int index, boolean aliceOrBobTurn) {
    if (index == stoneValue.length) {
      return new Score(0, 0);
    }

    State state = new State(index, aliceOrBobTurn);
    if (!cache.containsKey(state)) {
      int aliceScore = Integer.MIN_VALUE;
      int bobScore = Integer.MIN_VALUE;
      int current = 0;
      for (int i = 0; i < 3 && index + i < stoneValue.length; ++i) {
        current += stoneValue[index + i];
        Score subScore = search(stoneValue, index + i + 1, !aliceOrBobTurn);

        if (aliceOrBobTurn) {
          if (current + subScore.alice() > aliceScore) {
            aliceScore = current + subScore.alice();
            bobScore = subScore.bob();
          }
        } else {
          if (current + subScore.bob() > bobScore) {
            bobScore = current + subScore.bob();
            aliceScore = subScore.alice();
          }
        }
      }

      cache.put(state, new Score(aliceScore, bobScore));
    }

    return cache.get(state);
  }
}

record State(int index, boolean aliceOrBobTurn) {}

record Score(int alice, int bob) {}
