import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] numsSameConsecDiff(int N, int K) {
		List<Integer> solutions = new ArrayList<>();
		for (int digit = 0; digit <= 9; ++digit) {
			search(N, K, solutions, String.valueOf(digit));
		}

		return solutions.stream().mapToInt(x -> x).toArray();
	}

	void search(int N, int K, List<Integer> solutions, String current) {
		if (current.length() == N) {
			solutions.add(Integer.parseInt(current));

			return;
		}

		if (current.startsWith("0")) {
			return;
		}

		int lastDigit = current.charAt(current.length() - 1) - '0';
		if (lastDigit + K <= 9) {
			search(N, K, solutions, current + (lastDigit + K));
		}

		if (K != 0 && lastDigit - K >= 0) {
			search(N, K, solutions, current + (lastDigit - K));
		}
	}
}
