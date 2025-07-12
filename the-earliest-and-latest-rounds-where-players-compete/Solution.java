import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  Map<State, int[]> cache = new HashMap<>();

  public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
    State state = new State(n, firstPlayer, secondPlayer);
    if (!cache.containsKey(state)) {
      int minRound;
      int maxRound;
      if (firstPlayer + secondPlayer == n + 1) {
        minRound = 1;
        maxRound = 1;
      } else {
        minRound = Integer.MAX_VALUE;
        maxRound = Integer.MIN_VALUE;

        int round = n / 2;
        for (int mask = 0; mask < 1 << round; ++mask) {
          List<Integer> winners = new ArrayList<>();
          if (n % 2 != 0) {
            winners.add((n + 1) / 2);
          }
          for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if (((mask >> i) & 1) == 0) {
              winners.add(i + 1);
            } else {
              winners.add(j + 1);
            }
          }
          Collections.sort(winners);

          int nextFirstPlayer = find(winners, firstPlayer);
          int nextSecondPlayer = find(winners, secondPlayer);
          if (nextFirstPlayer != -1 && nextSecondPlayer != -1) {
            int[] subResult = earliestAndLatest(winners.size(), nextFirstPlayer, nextSecondPlayer);

            minRound = Math.min(minRound, subResult[0] + 1);
            maxRound = Math.max(maxRound, subResult[1] + 1);
          }
        }
      }

      cache.put(state, new int[] {minRound, maxRound});
    }

    return cache.get(state);
  }

  int find(List<Integer> winners, int player) {
    for (int i = 0; i < winners.size(); ++i) {
      if (winners.get(i) == player) {
        return i + 1;
      }
    }

    return -1;
  }
}

record State(int n, int firstPlayer, int secondPlayer) {}
