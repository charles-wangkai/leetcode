import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  public int minimumOperationsToMakeEqual(int x, int y) {
    int[] distances = new int[Math.max(x + 10, y * 11) + 1];
    Arrays.fill(distances, -1);
    distances[x] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(x);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      if (head % 11 == 0 && distances[head / 11] == -1) {
        distances[head / 11] = distances[head] + 1;
        queue.offer(head / 11);
      }
      if (head % 5 == 0 && distances[head / 5] == -1) {
        distances[head / 5] = distances[head] + 1;
        queue.offer(head / 5);
      }
      if (head != 1 && distances[head - 1] == -1) {
        distances[head - 1] = distances[head] + 1;
        queue.offer(head - 1);
      }
      if (head + 1 != distances.length && distances[head + 1] == -1) {
        distances[head + 1] = distances[head] + 1;
        queue.offer(head + 1);
      }
    }

    return distances[y];
  }
}