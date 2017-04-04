import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FriendCircles {
	public int findCircleNum(int[][] M) {
		int[] parents = new int[M.length];
		Arrays.fill(parents, -1);

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < i; j++) {
				if (M[i][j] == 1) {
					int rootI = findRoot(parents, i);
					int rootJ = findRoot(parents, j);
					if (rootI != rootJ) {
						parents[rootJ] = rootI;
					}
				}
			}
		}

		Set<Integer> roots = new HashSet<Integer>();
		for (int i = 0; i < parents.length; i++) {
			roots.add(findRoot(parents, i));
		}
		return roots.size();
	}

	int findRoot(int[] parents, int node) {
		int root = node;
		while (parents[root] != -1) {
			root = parents[root];
		}

		int index = node;
		while (index != root) {
			int nextIndex = parents[index];
			parents[index] = root;

			index = nextIndex;
		}

		return root;
	}
}
