import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int shortestPathLength(int[][] graph) {
    int n = graph.length;

    int[][] steps = new int[1 << n][n];
    for (int i = 0; i < steps.length; ++i) {
      Arrays.fill(steps[i], Integer.MAX_VALUE);
    }

    Queue<State> queue = new ArrayDeque<>();
    for (int i = 0; i < n; ++i) {
      State state = new State(1 << i, i);

      steps[state.mask][state.currentNode] = 0;
      queue.offer(state);
    }

    while (!queue.isEmpty()) {
      State state = queue.poll();

      for (int nextNode : graph[state.currentNode]) {
        int nextMask = state.mask | (1 << nextNode);

        if (steps[nextMask][nextNode] == Integer.MAX_VALUE) {
          steps[nextMask][nextNode] = steps[state.mask][state.currentNode] + 1;
          queue.offer(new State(nextMask, nextNode));
        }
      }
    }

    return IntStream.range(0, n).map(i -> steps[(1 << n) - 1][i]).min().getAsInt();
  }
}

class State {
  int mask;
  int currentNode;

  State(int mask, int currentNode) {
    this.mask = mask;
    this.currentNode = currentNode;
  }
}