import java.util.ArrayList;
import java.util.List;

public class MyCalendar {
	List<Event> events = new ArrayList<Event>();

	public MyCalendar() {
	}

	public boolean book(int start, int end) {
		Event event = new Event(start, end);

		if (events.stream().anyMatch(e -> e.isIntersect(event))) {
			return false;
		}

		events.add(event);
		return true;
	}
}

class Event {
	int start;
	int end;

	Event(int start, int end) {
		this.start = start;
		this.end = end;
	}

	boolean isIntersect(Event other) {
		return !(end <= other.start || start >= other.end);
	}
}

/**
 * Your MyCalendar object will be instantiated and called as such: MyCalendar
 * obj = new MyCalendar(); boolean param_1 = obj.book(start,end);
 */