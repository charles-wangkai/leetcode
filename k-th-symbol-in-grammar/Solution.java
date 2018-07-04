public class Solution {
	public int kthGrammar(int N, int K) {
		return search(K);
	}

	int search(int K) {
		if (K == 1) {
			return 0;
		}

		int prev = search((K - 1) / 2 + 1);
		if (prev == 0) {
			if (K % 2 != 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (K % 2 != 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
