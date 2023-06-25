import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countRoutes(int[] locations, int start, int finish, int fuel) {
    int[][] dp = new int[locations.length][fuel + 1];
    dp[start][fuel] = 1;

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::rest).reversed());
    pq.offer(new Element(start, fuel));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      for (int i = 0; i < locations.length; ++i) {
        if (i != head.city() && Math.abs(locations[i] - locations[head.city()]) <= head.rest()) {
          int nextRest = head.rest() - Math.abs(locations[i] - locations[head.city()]);

          if (dp[i][nextRest] == 0) {
            pq.offer(new Element(i, nextRest));
          }
          dp[i][nextRest] = addMod(dp[i][nextRest], dp[head.city()][head.rest()]);
        }
      }
    }

    return Arrays.stream(dp[finish]).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record Element(int city, int rest) {}
