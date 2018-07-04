import java.util.ArrayList;
import java.util.List;

public class Solution {
	public String getPermutation(int n, int k) {
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}

		List<Integer> elements = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			elements.add(i);
		}

		StringBuilder permutation = new StringBuilder();
		for (int i = 0; i < n; i++) {
			factorial /= n - i;
			int index = (k - 1) / factorial;
			permutation.append(elements.remove(index));
			k -= index * factorial;
		}
		return permutation.toString();
	}
}
