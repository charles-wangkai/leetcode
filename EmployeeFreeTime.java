import java.util.ArrayList;
import java.util.Collections;
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

public class EmployeeFreeTime {
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		List<Terminal> terminals = new ArrayList<Terminal>();
		for (List<Interval> intervals : schedule) {
			for (Interval interval : intervals) {
				terminals.add(new Terminal(interval.start, false));
				terminals.add(new Terminal(interval.end, true));
			}
		}
		Collections.sort(terminals,
				(t1, t2) -> t1.x != t2.x ? Integer.compare(t1.x, t2.x) : Boolean.compare(t1.endOrStart, t2.endOrStart));

		int prev = -1;
		int work = 0;
		List<Interval> freeTimes = new ArrayList<Interval>();
		for (Terminal terminal : terminals) {
			if (work == 0 && prev >= 0) {
				freeTimes.add(new Interval(prev, terminal.x));
			}

			if (terminal.endOrStart) {
				work--;
			} else {
				work++;
			}

			prev = terminal.x;
		}
		return freeTimes;
	}
}

class Terminal {
	int x;
	boolean endOrStart;

	Terminal(int x, boolean endOrStart) {
		this.x = x;
		this.endOrStart = endOrStart;
	}
}