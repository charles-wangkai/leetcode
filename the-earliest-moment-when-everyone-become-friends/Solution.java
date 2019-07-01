import java.util.Arrays;

public class Solution {
	public int earliestAcq(int[][] logs, int N) {
		Arrays.sort(logs, (log1, log2) -> Integer.compare(log1[0], log2[0]));

		int groupNum = N;
		int[] parents = new int[N];
		Arrays.fill(parents, -1);

		for (int[] log : logs) {
			int root1 = findRoot(parents, log[1]);
			int root2 = findRoot(parents, log[2]);

			if (root1 != root2) {
				parents[root2] = root1;

				groupNum--;
				if (groupNum == 1) {
					return log[0];
				}
			}
		}

		return -1;
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
