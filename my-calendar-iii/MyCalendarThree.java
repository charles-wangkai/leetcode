import java.util.SortedMap;
import java.util.TreeMap;

class MyCalendarThree {
  SortedMap<Integer, Integer> timeToDelta = new TreeMap<>();

  public int book(int startTime, int endTime) {
    timeToDelta.put(startTime, timeToDelta.getOrDefault(startTime, 0) + 1);
    timeToDelta.put(endTime, timeToDelta.getOrDefault(endTime, 0) - 1);

    int result = 0;
    int depth = 0;
    for (int delta : timeToDelta.values()) {
      depth += delta;
      result = Math.max(result, depth);
    }

    return result;
  }
}

// Your MyCalendarThree object will be instantiated and called as such:
// MyCalendarThree obj = new MyCalendarThree();
// int param_1 = obj.book(start,end);
