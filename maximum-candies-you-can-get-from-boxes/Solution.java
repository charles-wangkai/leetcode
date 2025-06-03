import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public int maxCandies(
      int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
    int n = status.length;

    boolean[] processed = new boolean[n];
    boolean[] found = new boolean[n];
    boolean[] hasKeys = new boolean[n];

    Queue<Integer> queue = new ArrayDeque<>();
    for (int initialBox : initialBoxes) {
      found[initialBox] = true;
      queue.offer(initialBox);
    }

    int result = 0;
    while (!queue.isEmpty()) {
      int box = queue.poll();
      if (!processed[box] && found[box] && (status[box] == 1 || hasKeys[box])) {
        processed[box] = true;
        result += candies[box];

        for (int key : keys[box]) {
          hasKeys[key] = true;
          queue.offer(key);
        }

        for (int containedBox : containedBoxes[box]) {
          found[containedBox] = true;
          queue.offer(containedBox);
        }
      }
    }

    return result;
  }
}
