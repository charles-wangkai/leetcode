import java.util.Arrays;
import java.util.PriorityQueue;

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
	public int minMeetingRooms(Interval[] intervals) {
		Arrays.sort(intervals, (interval1, interval2) -> interval1.start
				- interval2.start);

		int maxMeetingNum = 0;
		PriorityQueue<Integer> ends = new PriorityQueue<Integer>();
		for (Interval interval : intervals) {
			while (!ends.isEmpty() && ends.peek() <= interval.start) {
				ends.poll();
			}
			ends.offer(interval.end);
			maxMeetingNum = Math.max(maxMeetingNum, ends.size());
		}
		return maxMeetingNum;
	}
}
