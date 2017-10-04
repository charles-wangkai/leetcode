public class RepeatedStringMatch {
	public int repeatedStringMatch(String A, String B) {
		int minTimes = -1;
		for (int i = 0; i < A.length(); i++) {
			int times = findTimes(A, i, B);
			if (times > 0 && (minTimes < 0 || times < minTimes)) {
				minTimes = times;
			}
		}
		return minTimes;
	}

	int findTimes(String A, int indexA, String B) {
		int times = 1;
		for (int i = 0; i < B.length(); i++) {
			if (A.charAt(indexA) != B.charAt(i)) {
				return -1;
			}

			if (i + 1 < B.length()) {
				indexA++;
				if (indexA == A.length()) {
					indexA = 0;
					times++;
				}
			}
		}
		return times;
	}
}
