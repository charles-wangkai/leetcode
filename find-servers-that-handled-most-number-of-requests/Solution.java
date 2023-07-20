import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
    int[] counts = new int[k];
    NavigableSet<Integer> availables = new TreeSet<>();
    for (int i = 0; i < k; ++i) {
      availables.add(i);
    }
    PriorityQueue<Element> elements =
        new PriorityQueue<>((e1, e2) -> Integer.compare(e1.finishTime, e2.finishTime));

    for (int i = 0; i < arrival.length; ++i) {
      while (!elements.isEmpty() && elements.peek().finishTime <= arrival[i]) {
        availables.add(elements.poll().index);
      }

      int available = findAvailable(availables, i % k);
      if (available != -1) {
        ++counts[available];
        availables.remove(available);
        elements.offer(new Element(available, arrival[i] + load[i]));
      }
    }

    int maxCount = Arrays.stream(counts).max().getAsInt();

    return IntStream.range(0, k).filter(i -> counts[i] == maxCount).boxed().toList();
  }

  int findAvailable(NavigableSet<Integer> availables, int lower) {
    if (availables.isEmpty()) {
      return -1;
    }

    Integer ceiling = availables.ceiling(lower);
    if (ceiling != null) {
      return ceiling;
    }

    return availables.first();
  }
}

class Element {
  int index;
  int finishTime;

  Element(int index, int finishTime) {
    this.index = index;
    this.finishTime = finishTime;
  }
}
