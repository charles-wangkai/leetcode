import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    PriorityQueue<Integer> restProjects =
        new PriorityQueue<>(Comparator.comparing(i -> capital[i]));
    for (int i = 0; i < profits.length; ++i) {
      restProjects.add(i);
    }

    PriorityQueue<Integer> readyProjects =
        new PriorityQueue<>(Comparator.comparing((Integer i) -> profits[i]).reversed());

    int result = w;
    for (int i = 0; i < Math.min(k, profits.length); ++i) {
      while (!restProjects.isEmpty() && capital[restProjects.peek()] <= result) {
        readyProjects.add(restProjects.poll());
      }

      if (readyProjects.isEmpty()) {
        break;
      }

      result += profits[readyProjects.poll()];
    }

    return result;
  }
}
