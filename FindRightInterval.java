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

public class FindRightInterval {
	public int[] findRightInterval(Interval[] intervals) {
		IndexAndInterval[] iis = new IndexAndInterval[intervals.length];
		for (int i = 0; i < iis.length; i++) {
			iis[i] = new IndexAndInterval(i, intervals[i]);
		}

		Arrays.sort(iis);

		int[] result = new int[intervals.length];
		for (int i = 0; i < result.length; i++) {
			int index = Arrays.binarySearch(iis, new IndexAndInterval(-1, new Interval(iis[i].interval.end, -1)));
			if (index < 0) {
				index = -1 - index;
			}
			if (index == iis.length) {
				index = -1;
			}
			result[iis[i].index] = (index >= 0) ? iis[index].index : index;
		}
		return result;
	}
}

class IndexAndInterval implements Comparable<IndexAndInterval> {
	int index;
	Interval interval;

	IndexAndInterval(int index, Interval interval) {
		this.index = index;
		this.interval = interval;
	}

	@Override
	public int compareTo(IndexAndInterval other) {
		return interval.start - other.interval.start;
	}
}