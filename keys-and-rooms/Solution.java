import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int N = rooms.size();
    boolean[] visited = new boolean[N];
    visited[0] = true;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
      int room = queue.poll();

      for (int nextRoom : rooms.get(room)) {
        if (!visited[nextRoom]) {
          visited[nextRoom] = true;
          queue.offer(nextRoom);
        }
      }
    }

    return IntStream.range(0, N).allMatch(i -> visited[i]);
  }
}
