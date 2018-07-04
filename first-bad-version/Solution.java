// The isBadVersion API is defined in the parent class VersionControl.
class VersionControl {
	boolean isBadVersion(int version) {
		throw new UnsupportedOperationException();
	}
}

public class Solution extends VersionControl {
	public int firstBadVersion(int n) {
		int minBadVersion = Integer.MAX_VALUE;
		int lower = 1;
		int upper = n;
		while (lower <= upper) {
			int middle = lower + (upper - lower) / 2;
			if (isBadVersion(middle)) {
				minBadVersion = middle;
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return minBadVersion;
	}
}
