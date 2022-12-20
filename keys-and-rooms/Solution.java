import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] visited = new boolean[rooms.size()];
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

    return IntStream.range(0, visited.length).allMatch(i -> visited[i]);
  }
}
