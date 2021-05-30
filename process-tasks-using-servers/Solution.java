import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public int[] assignTasks(int[] servers, int[] tasks) {
    PriorityQueue<Event> eventPq =
        new PriorityQueue<>(
            Comparator.comparing((Event e) -> e.time)
                .thenComparing(e -> e.start)
                .thenComparing(e -> e.taskIndex));
    for (int i = 0; i < tasks.length; ++i) {
      eventPq.offer(new Event(i, i, true));
    }

    PriorityQueue<Integer> serverPq =
        new PriorityQueue<>(Comparator.comparing((Integer s) -> servers[s]).thenComparing(s -> s));
    for (int i = 0; i < servers.length; ++i) {
      serverPq.offer(i);
    }

    SortedSet<Integer> waitingTaskIndices = new TreeSet<>();

    int[] result = new int[tasks.length];
    while (!eventPq.isEmpty()) {
      Event head = eventPq.poll();
      if (head.start) {
        if (serverPq.isEmpty()) {
          waitingTaskIndices.add(head.taskIndex);
        } else {
          result[head.taskIndex] = serverPq.poll();

          eventPq.offer(new Event(head.time + tasks[head.taskIndex], head.taskIndex, false));
        }
      } else {
        int serverIndex = result[head.taskIndex];
        serverPq.offer(serverIndex);

        if (!waitingTaskIndices.isEmpty()) {
          int taskIndex = waitingTaskIndices.first();
          waitingTaskIndices.remove(taskIndex);

          eventPq.offer(new Event(head.time, taskIndex, true));
        }
      }
    }

    return result;
  }
}

class Event {
  long time;
  int taskIndex;
  boolean start;

  Event(long time, int taskIndex, boolean start) {
    this.time = time;
    this.taskIndex = taskIndex;
    this.start = start;
  }
}
