import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minimumRounds(int[] tasks) {
    Map<Integer, Integer> taskToCount = new HashMap<>();
    for (int task : tasks) {
      taskToCount.put(task, taskToCount.getOrDefault(task, 0) + 1);
    }

    if (taskToCount.values().stream().anyMatch(count -> count == 1)) {
      return -1;
    }

    return taskToCount.values().stream().mapToInt(count -> (count + 2) / 3).sum();
  }
}