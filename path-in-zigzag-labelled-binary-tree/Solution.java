import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<Integer> pathInZigZagTree(int label) {
		List<Integer> path = new ArrayList<>();
		for (int i = label; i != 0; i /= 2) {
			path.add(i);
		}

		Collections.reverse(path);

		for (int i = path.size() - 2; i >= 0; i -= 2) {
			path.set(i, (1 << i) + (1 << (i + 1)) - 1 - path.get(i));
		}

		return path;
	}
}
