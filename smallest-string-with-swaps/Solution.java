import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int[] parents = new int[s.length()];
		Arrays.fill(parents, -1);

		for (List<Integer> pair : pairs) {
			int a = pair.get(0);
			int b = pair.get(1);

			int rootA = findRoot(parents, a);
			int rootB = findRoot(parents, b);
			if (rootA != rootB) {
				parents[rootB] = rootA;
			}
		}

		Map<Integer, List<Integer>> rootToIndices = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			int root = findRoot(parents, i);
			if (!rootToIndices.containsKey(root)) {
				rootToIndices.put(root, new ArrayList<>());
			}

			rootToIndices.get(root).add(i);
		}

		char[] result = new char[s.length()];
		for (List<Integer> indices : rootToIndices.values()) {
			List<Character> letters = indices.stream().map(s::charAt).sorted().collect(Collectors.toList());

			for (int i = 0; i < indices.size(); i++) {
				result[indices.get(i)] = letters.get(i);
			}
		}

		return new String(result);
	}

	int findRoot(int[] parents, int node) {
		int root = node;
		while (parents[root] != -1) {
			root = parents[root];
		}

		int p = node;
		while (p != root) {
			int next = parents[p];
			parents[p] = root;

			p = next;
		}

		return root;
	}
}
