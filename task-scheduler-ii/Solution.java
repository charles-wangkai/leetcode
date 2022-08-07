import java.util.HashMap;
import java.util.Map;

class Solution {
  public long taskSchedulerII(int[] tasks, int space) {
    long day = 0;
    Map<Integer, Long> typeToNextDay = new HashMap<>();
    for (int task : tasks) {
      day = Math.max(day + 1, typeToNextDay.getOrDefault(task, 0L));
      typeToNextDay.put(task, day + space + 1);
    }

    return day;
  }
}