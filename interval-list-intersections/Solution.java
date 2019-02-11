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

public class Solution {
	public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
		List<Interval> result = new ArrayList<>();

		int indexA = 0;
		int indexB = 0;
		while (indexA < A.length && indexB < B.length) {
			Interval intervalA = A[indexA];
			Interval intervalB = B[indexB];

			if (intervalA.end <= intervalB.end) {
				if (intervalB.start <= intervalA.end) {
					result.add(new Interval(Math.max(intervalA.start, intervalB.start), intervalA.end));

					B[indexB] = new Interval(intervalA.end, intervalB.end);
				}

				indexA++;
			} else {
				if (intervalA.start <= intervalB.end) {
					result.add(new Interval(Math.max(intervalA.start, intervalB.start), intervalB.end));

					A[indexA] = new Interval(intervalB.end, intervalA.end);
				}

				indexB++;
			}
		}

		return result.toArray(new Interval[0]);
	}
}
