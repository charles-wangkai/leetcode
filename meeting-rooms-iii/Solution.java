import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int mostBooked(int n, int[][] meetings) {
    Arrays.sort(meetings, Comparator.comparing(meeting -> meeting[0]));

    int[] counts = new int[n];
    SortedSet<Integer> availables = new TreeSet<>();
    for (int i = 0; i < n; ++i) {
      availables.add(i);
    }
    PriorityQueue<EndEvent> endEvents =
        new PriorityQueue<>(Comparator.comparing(EndEvent::time).thenComparing(EndEvent::room));
    for (int[] meeting : meetings) {
      while (!endEvents.isEmpty() && endEvents.peek().time() <= meeting[0]) {
        availables.add(endEvents.poll().room());
      }

      int room;
      if (availables.isEmpty()) {
        EndEvent endEvent = endEvents.poll();
        room = endEvent.room();
        endEvents.add(new EndEvent(endEvent.time() + (meeting[1] - meeting[0]), room));
      } else {
        room = availables.first();
        availables.remove(room);
        endEvents.add(new EndEvent(meeting[1], room));
      }

      ++counts[room];
    }

    int maxCount = Arrays.stream(counts).max().getAsInt();

    return IntStream.range(0, counts.length).filter(i -> counts[i] == maxCount).min().getAsInt();
  }
}

record EndEvent(long time, int room) {}
