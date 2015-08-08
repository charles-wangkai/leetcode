import java.util.Arrays;

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

public class MeetingRooms {
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, (interval1, interval2) -> interval1.start
				- interval2.start);
		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i].end > intervals[i + 1].start) {
				return false;
			}
		}
		return true;
	}
}
