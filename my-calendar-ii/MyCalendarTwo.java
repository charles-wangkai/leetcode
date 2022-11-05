import java.util.SortedMap;
import java.util.TreeMap;

class MyCalendarTwo {
  SortedMap<Integer, Integer> timeToDelta = new TreeMap<>();

  public boolean book(int start, int end) {
    timeToDelta.put(start, timeToDelta.getOrDefault(start, 0) + 1);
    timeToDelta.put(end, timeToDelta.getOrDefault(end, 0) - 1);

    int depth = 0;
    for (int delta : timeToDelta.values()) {
      depth += delta;
      if (depth >= 3) {
        timeToDelta.put(start, timeToDelta.get(start) - 1);
        timeToDelta.put(end, timeToDelta.get(end) + 1);

        return false;
      }
    }

    return true;
  }
}

// Your MyCalendarTwo object will be instantiated and called as such:
// MyCalendarTwo obj = new MyCalendarTwo();
// boolean param_1 = obj.book(start,end);
