import java.util.ArrayList;
import java.util.List;

class MyCalendar {
  List<Event> events = new ArrayList<>();

  public boolean book(int start, int end) {
    Event event = new Event(start, end);

    if (events.stream().anyMatch(e -> isIntersect(e, event))) {
      return false;
    }

    events.add(event);

    return true;
  }

  private boolean isIntersect(Event e1, Event e2) {
    return !(e1.end() <= e2.start() || e1.start() >= e2.end());
  }
}

record Event(int start, int end) {}

// Your MyCalendar object will be instantiated and called as such:
// MyCalendar obj = new MyCalendar();
// boolean param_1 = obj.book(start,end);
