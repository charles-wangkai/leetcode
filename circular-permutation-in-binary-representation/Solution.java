import java.util.ArrayList;
import java.util.List;

public class Solution {
	int current;

	public List<Integer> circularPermutation(int n, int start) {
		current = start;
		List<Integer> result = new ArrayList<>();
		result.add(current);
		search(result, 1 << (n - 1));

		return result;
	}

	void search(List<Integer> result, int mask) {
		if (mask == 0) {
			return;
		}

		search(result, mask >> 1);

		current ^= mask;
		result.add(current);

		search(result, mask >> 1);
	}
}
