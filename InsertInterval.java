import java.util.ArrayList;
import java.util.List;

// Definition for an interval.
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class InsertInterval {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> inserted = new ArrayList<Interval>();
		boolean used = false;
		for (int i = 0; i < intervals.size() || !used; i++) {
			Interval selected;
			if (i < intervals.size()
					&& (used || intervals.get(i).start < newInterval.start)) {
				selected = intervals.get(i);
			} else {
				selected = newInterval;
				used = true;
				i--;
			}

			if (inserted.isEmpty()
					|| inserted.get(inserted.size() - 1).end < selected.start) {
				inserted.add(selected);
			} else {
				Interval last = inserted.get(inserted.size() - 1);
				if (last.end < selected.end) {
					inserted.remove(inserted.size() - 1);
					inserted.add(new Interval(last.start, selected.end));
				}
			}
		}
		return inserted;
	}
}
