import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Solution {
  public int[] exclusiveTime(int n, List<String> logs) {
    int[] times = new int[n];

    int prevTime = -1;
    Deque<Integer> ids = new ArrayDeque<>();
    for (String log : logs) {
      String[] fields = log.split(":");
      int id = Integer.parseInt(fields[0]);
      String command = fields[1];
      int timestamp = Integer.parseInt(fields[2]);

      if (command.equals("start")) {
        if (!ids.isEmpty()) {
          times[ids.peek()] += timestamp - prevTime;
        }

        ids.push(id);
        prevTime = timestamp;
      } else {
        times[id] += timestamp + 1 - prevTime;

        ids.pop();
        prevTime = timestamp + 1;
      }
    }

    return times;
  }
}
