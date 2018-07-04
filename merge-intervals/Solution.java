import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class Solution {
	public List<Interval> merge(List<Interval> intervals) {
		Comparator<Interval> intervalComparator = new Comparator<Interval>() {
			@Override
			public int compare(Interval interval1, Interval interval2) {
				return interval1.start - interval2.start;
			}
		};
		Collections.sort(intervals, intervalComparator);

		List<Interval> merged = new ArrayList<Interval>();
		for (Interval interval : intervals) {
			if (merged.isEmpty()
					|| merged.get(merged.size() - 1).end < interval.start) {
				merged.add(interval);
			} else {
				Interval last = merged.get(merged.size() - 1);
				if (last.end < interval.end) {
					merged.remove(merged.size() - 1);
					merged.add(new Interval(last.start, interval.end));
				}
			}
		}
		return merged;
	}
}
