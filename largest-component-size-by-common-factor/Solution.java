import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int largestComponentSize(int[] A) {
		int maxValue = Arrays.stream(A).max().getAsInt();

		int[] parents = new int[maxValue + 1];
		Arrays.fill(parents, -1);
		for (int a : A) {
			parents[a] = a;
		}

		boolean[] primes = new boolean[parents.length];
		Arrays.fill(primes, true);
		for (int i = 2; i < parents.length; i++) {
			if (primes[i]) {
				int last = -1;

				for (int j = i; j < primes.length; j += i) {
					primes[j] = false;

					if (parents[j] != -1) {
						if (last != -1) {
							union(parents, j, last);
						}

						last = j;
					}
				}
			}
		}

		Map<Integer, Integer> rootToSize = new HashMap<>();
		for (int i = 0; i < parents.length; i++) {
			if (parents[i] != -1) {
				int root = findRoot(parents, i);
				rootToSize.put(root, rootToSize.getOrDefault(root, 0) + 1);
			}
		}

		return rootToSize.values().stream().mapToInt(x -> x).max().getAsInt();
	}

	static void union(int[] parents, int value1, int value2) {
		int root1 = findRoot(parents, value1);
		int root2 = findRoot(parents, value2);

		if (root1 != root2) {
			parents[root2] = root1;
		}
	}

	static int findRoot(int[] parents, int value) {
		int root = value;
		while (parents[root] != root) {
			root = parents[root];
		}

		int p = value;
		while (p != root) {
			int next = parents[p];
			parents[p] = root;

			p = next;
		}

		return root;
	}
}
