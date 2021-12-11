import java.util.Comparator;
import java.util.PriorityQueue;

class SORTracker {
  PriorityQueue<Location> prevs =
      new PriorityQueue<>(
          Comparator.comparing((Location l) -> l.score)
              .thenComparing(Comparator.comparing((Location l) -> l.name).reversed()));
  PriorityQueue<Location> nexts =
      new PriorityQueue<>(
          Comparator.comparing((Location l) -> l.score).reversed().thenComparing(l -> l.name));
  int count = 0;

  public void add(String name, int score) {
    Location location = new Location(name, score);
    if (prevs.isEmpty() || isBetter(prevs.peek(), location)) {
      nexts.offer(location);
    } else {
      nexts.offer(prevs.poll());
      prevs.offer(location);
    }
  }

  boolean isBetter(Location l1, Location l2) {
    return (l1.score != l2.score) ? (l1.score > l2.score) : l1.name.compareTo(l2.name) < 0;
  }

  public String get() {
    ++count;

    Location next = nexts.poll();
    prevs.offer(next);

    return next.name;
  }
}

class Location {
  String name;
  int score;

  Location(String name, int score) {
    this.name = name;
    this.score = score;
  }
}

// Your SORTracker object will be instantiated and called as such:
// SORTracker obj = new SORTracker();
// obj.add(name,score);
// String param_2 = obj.get();
