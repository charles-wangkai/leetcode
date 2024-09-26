import java.util.ArrayList;
import java.util.List;

class MyCalendar {
  List<Event> events = new ArrayList<>();

  public boolean book(int start, int end) {
    Event event = new Event(start, end);
    if (events.stream().anyMatch(e -> !(e.end() <= event.start() || e.start() >= event.end()))) {
      return false;
    }

    events.add(event);

    return true;
  }
}

record Event(int start, int end) {}

// Your MyCalendar object will be instantiated and called as such:
// MyCalendar obj = new MyCalendar();
// boolean param_1 = obj.book(start,end);
