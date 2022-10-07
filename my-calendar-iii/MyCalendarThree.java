import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class MyCalendarThree {
  private SortedSet<Event> events = new TreeSet<>(Comparator.comparing(Event::start));
  private int maxCount = 1;

  public int book(int start, int end) {
    int start_ = start;
    List<Event> impacts =
        events.stream().filter(e -> !(e.start() >= end || e.end() <= start_)).toList();
    events.removeAll(impacts);

    if (!impacts.isEmpty()) {
      Event firstImpact = impacts.get(0);
      if (firstImpact.start() < start) {
        events.add(new Event(firstImpact.start(), start, firstImpact.count()));
      }

      Event lastImpact = impacts.get(impacts.size() - 1);
      if (lastImpact.end() > end) {
        events.add(new Event(end, lastImpact.end(), lastImpact.count()));
      }
    }

    for (Event impact : impacts) {
      if (start < impact.start()) {
        events.add(new Event(start, impact.start(), 1));
      }

      int currStart = Math.max(start, impact.start());
      int nextStart = Math.min(end, impact.end());
      events.add(new Event(currStart, nextStart, impact.count() + 1));
      maxCount = Math.max(maxCount, impact.count() + 1);

      start = nextStart;
    }
    if (start < end) {
      events.add(new Event(start, end, 1));
    }

    return maxCount;
  }
}

record Event(int start, int end, int count) {}

// Your MyCalendarThree object will be instantiated and called as such:
// MyCalendarThree obj = new MyCalendarThree();
// int param_1 = obj.book(start,end);
