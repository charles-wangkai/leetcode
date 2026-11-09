import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
    NavigableSet<Integer> availables = new TreeSet<>();
    for (int i = 0; i < k; ++i) {
      availables.add(i);
    }

    PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparing(Event::finishTime));

    int[] counts = new int[k];
    for (int i = 0; i < arrival.length; ++i) {
      while (!events.isEmpty() && events.peek().finishTime() <= arrival[i]) {
        availables.add(events.poll().index());
      }

      if (!availables.isEmpty()) {
        int available = findAvailable(availables, i % k);

        ++counts[available];
        availables.remove(available);
        events.offer(new Event(available, arrival[i] + load[i]));
      }
    }

    int maxCount = Arrays.stream(counts).max().getAsInt();

    return IntStream.range(0, k).filter(i -> counts[i] == maxCount).boxed().toList();
  }

  int findAvailable(NavigableSet<Integer> availables, int lower) {
    Integer ceiling = availables.ceiling(lower);

    return (ceiling != null) ? ceiling : availables.first();
  }
}

record Event(int index, int finishTime) {}
