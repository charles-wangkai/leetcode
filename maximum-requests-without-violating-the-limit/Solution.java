import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int maxRequests(int[][] requests, int k, int window) {
    Arrays.sort(requests, Comparator.comparing(request -> request[1]));

    int result = 0;
    Map<Integer, Queue<Integer>> userToTimes = new HashMap<>();
    for (int[] request : requests) {
      int user = request[0];
      int time = request[1];

      userToTimes.putIfAbsent(user, new ArrayDeque<>());
      Queue<Integer> times = userToTimes.get(user);

      while (!times.isEmpty() && times.peek() < time - window) {
        times.poll();
      }

      if (times.size() + 1 <= k) {
        times.offer(time);
        ++result;
      }
    }

    return result;
  }
}