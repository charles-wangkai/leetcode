import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  static final int LIMIT = 10000;

  public int minimumJumps(int[] forbidden, int a, int b, int x) {
    Set<Integer> forbiddens = Arrays.stream(forbidden).boxed().collect(Collectors.toSet());

    boolean[][] visited = new boolean[LIMIT][2];
    visited[0][0] = true;
    Queue<State> queue = new LinkedList<>();
    queue.offer(new State(0, 0, 0));
    while (!queue.isEmpty()) {
      State head = queue.poll();
      if (head.value == x) {
        return head.jumpNum;
      }

      if (head.value + a < LIMIT
          && !forbiddens.contains(head.value + a)
          && !visited[head.value + a][0]) {
        visited[head.value + a][0] = true;
        queue.offer(new State(head.jumpNum + 1, head.value + a, 0));
      }
      if (head.backCount != 1
          && head.value - b >= 0
          && !forbiddens.contains(head.value - b)
          && !visited[head.value - b][1]) {
        visited[head.value - b][1] = true;
        queue.offer(new State(head.jumpNum + 1, head.value - b, 1));
      }
    }

    return -1;
  }
}

class State {
  int jumpNum;
  int value;
  int backCount;

  State(int jumpNum, int value, int backCount) {
    this.jumpNum = jumpNum;
    this.value = value;
    this.backCount = backCount;
  }
}
