import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int repeatedNTimes(int[] A) {
		Set<Integer> history = new HashSet<>();
		for (int i = 0;; i++) {
			if (history.contains(A[i])) {
				return A[i];
			}

			history.add(A[i]);
		}
	}
}
