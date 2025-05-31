import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  public int snakesAndLadders(int[][] board) {
    int[] destinations = flatten(board);

    int[] moveNums = new int[destinations.length];
    Arrays.fill(moveNums, -1);
    moveNums[0] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int current = queue.poll();
      for (int i = current + 1; i <= current + 6 && i < destinations.length; ++i) {
        int next = (destinations[i] == -1) ? i : destinations[i];
        if (moveNums[next] == -1) {
          moveNums[next] = moveNums[current] + 1;
          queue.offer(next);
        }
      }
    }

    return moveNums[moveNums.length - 1];
  }

  int[] flatten(int[][] board) {
    int n = board.length;

    int r = n - 1;
    int c = -1;
    int offsetC = 1;
    int[] destinations = new int[n * n];
    for (int i = 0; i < destinations.length; ++i) {
      c += offsetC;
      if (c == n || c == -1) {
        c -= offsetC;
        offsetC *= -1;
        --r;
      }

      destinations[i] = (board[r][c] == -1) ? -1 : (board[r][c] - 1);
    }

    return destinations;
  }
}
