import java.util.HashSet;
import java.util.Set;

public class Solution {
	public String crackSafe(int n, int k) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			result.append('0');
		}

		Set<String> visited = new HashSet<String>();
		visited.add(result.toString());

		search(result, pow(k, n), visited, n, k);

		return result.toString();
	}

	boolean search(StringBuilder result, int targetLength, Set<String> visited, int n, int k) {
		if (visited.size() == targetLength) {
			return true;
		}

		for (int digit = 0; digit < k; digit++) {
			result.append(digit);

			String next = result.substring(result.length() - n);
			if (!visited.contains(next)) {
				visited.add(next);

				if (search(result, targetLength, visited, n, k)) {
					return true;
				}

				visited.remove(next);
			}

			result.deleteCharAt(result.length() - 1);
		}
		return false;
	}

	int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}
