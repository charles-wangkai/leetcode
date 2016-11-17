public class KthSmallestInLexicographicalOrder {
	public int findKthNumber(int n, int k) {
		int result = 1;
		k--;

		while (k > 0) {
			int count = countPrefixBy(n, result);

			if (k >= count) {
				result++;
				k -= count;
			} else {
				result *= 10;
				k--;
			}
		}

		return result;
	}

	int countPrefixBy(int n, int prefix) {
		int count = 0;
		for (long first = prefix, last = prefix + 1; first <= n; first *= 10, last *= 10) {
			count += Math.min(n + 1, last) - first;
		}
		return count;
	}
}
