import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Solution {
  Map<State, int[]> cache = new HashMap<>();

  public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
    State state = new State(n, firstPlayer, secondPlayer);
    if (!cache.containsKey(state)) {
      int[] result;
      if (firstPlayer + secondPlayer == n + 1) {
        result = new int[] {1, 1};
      } else {
        int minRound = Integer.MAX_VALUE;
        int maxRound = Integer.MIN_VALUE;

        int round = n / 2;
        for (int code = 0; code < 1 << round; ++code) {
          List<Integer> winners = new ArrayList<>();
          if (n % 2 != 0) {
            winners.add((n + 1) / 2);
          }
          for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if ((code & (1 << i)) == 0) {
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

        result = new int[] {minRound, maxRound};
      }

      cache.put(state, result);
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

class State {
  int n;
  int firstPlayer;
  int secondPlayer;

  State(int n, int firstPlayer, int secondPlayer) {
    this.n = n;
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }

  @Override
  public int hashCode() {
    return Objects.hash(n, firstPlayer, secondPlayer);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return n == other.n && firstPlayer == other.firstPlayer && secondPlayer == other.secondPlayer;
  }
}
