import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public long maxProfit(int[] workers, int[][] tasks) {
    Map<Integer, PriorityQueue<Integer>> skillToProfits = new HashMap<>();
    for (int[] task : tasks) {
      skillToProfits.putIfAbsent(task[0], new PriorityQueue<>(Comparator.reverseOrder()));
      skillToProfits.get(task[0]).offer(task[1]);
    }

    long result = 0;
    for (int worker : workers) {
      if (skillToProfits.containsKey(worker)) {
        result += skillToProfits.get(worker).poll();

        if (skillToProfits.get(worker).isEmpty()) {
          skillToProfits.remove(worker);
        }
      }
    }

    if (!skillToProfits.isEmpty()) {
      result += skillToProfits.values().stream().mapToInt(PriorityQueue::peek).max().getAsInt();
    }

    return result;
  }
}