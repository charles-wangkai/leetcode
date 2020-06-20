import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
	public String getPermutation(int n, int k) {
		int factorial = IntStream.rangeClosed(1, n).reduce((x, y) -> x * y).getAsInt();

		List<Integer> elements = new ArrayList<>();
		for (int i = 1; i <= n; ++i) {
			elements.add(i);
		}

		StringBuilder permutation = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			factorial /= n - i;
			int index = (k - 1) / factorial;
			permutation.append(elements.remove(index));
			k -= index * factorial;
		}

		return permutation.toString();
	}
}
