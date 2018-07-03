import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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

public class SummaryRanges {
	/** Initialize your data structure here. */
	private TreeSet<Interval> intervals = new TreeSet<Interval>(
			(interval1, interval2) -> interval1.start - interval2.start);

	public SummaryRanges() {
	}

	public void addNum(int val) {
		Interval toAdd = new Interval(val, val);
		Interval floor = intervals.floor(toAdd);
		Interval ceiling = intervals.ceiling(toAdd);

		if (isWithin(val, floor) || isWithin(val, ceiling)) {
			return;
		}

		if (floor != null && ceiling != null & val == floor.end + 1 && val == ceiling.start - 1) {
			intervals.remove(floor);
			intervals.remove(ceiling);
			intervals.add(new Interval(floor.start, ceiling.end));
		} else if (floor != null && val == floor.end + 1) {
			intervals.remove(floor);
			intervals.add(new Interval(floor.start, val));
		} else if (ceiling != null && val == ceiling.start - 1) {
			intervals.remove(ceiling);
			intervals.add(new Interval(val, ceiling.end));
		} else {
			intervals.add(toAdd);
		}
	}

	private boolean isWithin(int val, Interval interval) {
		return interval != null && val >= interval.start && val <= interval.end;
	}

	public List<Interval> getIntervals() {
		return new ArrayList<Interval>(intervals);
	}
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges(); obj.addNum(val); List
 * <Interval> param_2 = obj.getIntervals();
 */