import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MyCalendar_II {
	NavigableSet<Event> events = new TreeSet<Event>((event1, event2) -> Integer.compare(event1.start, event2.start));

	public MyCalendar_II() {
	}

	public boolean book(int start, int end) {
		Event event = new Event(start, end, 0);
		List<Event> impacts = events.stream().filter(e -> e.isIntersect(event)).collect(Collectors.toList());

		if (impacts.stream().anyMatch(impact -> impact.count == 2)) {
			return false;
		}

		events.removeAll(impacts);
		if (!impacts.isEmpty()) {
			Event firstImpact = impacts.iterator().next();
			if (firstImpact.start < start) {
				events.add(new Event(firstImpact.start, start, firstImpact.count));
			}
		}
		for (Event impact : impacts) {
			if (start < impact.start) {
				events.add(new Event(start, impact.start, 1));
			}

			int currStart = Math.max(start, impact.start);
			int nextStart = Math.min(end, impact.end);
			events.add(new Event(currStart, nextStart, impact.count + 1));
			if (end < impact.end) {
				events.add(new Event(end, impact.end, impact.count));
			}

			start = nextStart;
		}
		if (start < end) {
			events.add(new Event(start, end, 1));
		}
		return true;
	}
}

class Event {
	int start;
	int end;
	int count;

	Event(int start, int end, int count) {
		this.start = start;
		this.end = end;
		this.count = count;
	}

	boolean isIntersect(Event other) {
		return !(end <= other.start || start >= other.end);
	}
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo(); boolean param_1 =
 * obj.book(start,end);
 */