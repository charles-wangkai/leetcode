public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (edges.length != n - 1) {
			return false;
		}

		if (n == 1) {
			return true;
		}

		boolean[] used = new boolean[n];
		for (int[] edge : edges) {
			for (int i = 0; i < edge.length; i++) {
				used[edge[i]] = true;
			}
		}

		for (boolean oneUsed : used) {
			if (!oneUsed) {
				return false;
			}
		}
		return true;
	}
}
