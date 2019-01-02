import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int[] numsSameConsecDiff(int N, int K) {
		List<Integer> result = new ArrayList<>();
		for (int digit = 0; digit <= 9; digit++) {
			search(N, K, result, String.valueOf(digit));
		}
		return result.stream().mapToInt(x -> x).toArray();
	}

	void search(int N, int K, List<Integer> result, String current) {
		if (current.length() == N) {
			result.add(Integer.parseInt(current));

			return;
		}

		if (current.startsWith("0")) {
			return;
		}

		int lastDigit = current.charAt(current.length() - 1) - '0';
		if (lastDigit + K <= 9) {
			search(N, K, result, current + (lastDigit + K));
		}

		if (K != 0 && lastDigit - K >= 0) {
			search(N, K, result, current + (lastDigit - K));
		}
	}
}
