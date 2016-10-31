import java.util.Arrays;
import java.util.Stack;

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

public class NonOverlappingIntervals {
	public int eraseOverlapIntervals(Interval[] intervals) {
		Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

		Stack<Interval> stack = new Stack<Interval>();
		for (Interval interval : intervals) {
			while (!stack.empty() && stack.peek().end > interval.end) {
				stack.pop();
			}

			if (stack.empty() || stack.peek().end <= interval.start) {
				stack.push(interval);
			}
		}
		return intervals.length - stack.size();
	}
}
