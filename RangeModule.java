import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class RangeModule {
	private NavigableSet<Range> ranges = new TreeSet<Range>((r1, r2) -> Integer.compare(r1.left, r2.left));

	public RangeModule() {
	}

	public void addRange(int left, int right) {
		removeRange(left, right);

		int addedLeft = left;
		Range lower = ranges.lower(new Range(left, left));
		if (lower != null && lower.right == left) {
			ranges.remove(lower);
			addedLeft = lower.left;
		}

		int addedRight = right;
		Range ceiling = ranges.ceiling(new Range(right, right));
		if (ceiling != null && ceiling.left == right) {
			ranges.remove(ceiling);
			addedRight = ceiling.right;
		}

		ranges.add(new Range(addedLeft, addedRight));
	}

	public boolean queryRange(int left, int right) {
		Range floor = ranges.floor(new Range(left, right));
		return floor != null && floor.right >= right;
	}

	public void removeRange(int left, int right) {
		Range lower = ranges.lower(new Range(left, left));
		if (lower != null && lower.right > left) {
			ranges.remove(lower);
			ranges.add(new Range(lower.left, left));

			if (lower.right > right) {
				ranges.add(new Range(right, lower.right));
			}
		}

		for (Range range : new ArrayList<Range>(ranges.subSet(new Range(left, left), new Range(right, right)))) {
			ranges.remove(range);

			if (range.right > right) {
				ranges.add(new Range(right, range.right));
			}
		}
	}
}

class Range {
	int left;
	int right;

	Range(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

/**
 * Your RangeModule object will be instantiated and called as such: RangeModule
 * obj = new RangeModule(); obj.addRange(left,right); boolean param_2 =
 * obj.queryRange(left,right); obj.removeRange(left,right);
 */