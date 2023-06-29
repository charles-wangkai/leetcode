import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int shortestPathAllKeys(String[] grid) {
    int m = grid.length;
    int n = grid[0].length();

    int keyCount = 0;
    Map<State, Integer> stateToMoveNum = new HashMap<>();
    Queue<State> queue = new ArrayDeque<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r].charAt(c) == '@') {
          State state = new State(r, c, Set.of());
          stateToMoveNum.put(state, 0);
          queue.offer(state);
        } else if (Character.isLowerCase(grid[r].charAt(c))) {
          ++keyCount;
        }
      }
    }

    while (!queue.isEmpty()) {
      State head = queue.poll();
      if (head.keys().size() == keyCount) {
        return stateToMoveNum.get(head);
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
          char cell = grid[adjR].charAt(adjC);
          if (cell != '#'
              && (!Character.isUpperCase(cell)
                  || head.keys().contains(Character.toLowerCase(cell)))) {
            Set<Character> nextKeys = new HashSet<>(head.keys());
            if (Character.isLowerCase(cell)) {
              nextKeys.add(cell);
            }

            State nextState = new State(adjR, adjC, nextKeys);
            if (!stateToMoveNum.containsKey(nextState)) {
              stateToMoveNum.put(nextState, stateToMoveNum.get(head) + 1);
              queue.offer(nextState);
            }
          }
        }
      }
    }

    return -1;
  }
}

record State(int r, int c, Set<Character> keys) {}
