import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedundantConnection {
	public int[] findRedundantConnection(int[][] edges) {
		for (int i = edges.length - 1;; i--) {
			if (isTree(edges, i)) {
				return edges[i];
			}
		}
	}

	boolean isTree(int[][] edges, int redundantIndex) {
		Set<Integer> children = new HashSet<Integer>();
		Map<Integer, Integer> node2root = new HashMap<Integer, Integer>();
		for (int i = 0; i < edges.length; i++) {
			if (i == redundantIndex) {
				continue;
			}

			int[] edge = edges[i];

			if (children.contains(edge[1])) {
				return false;
			}
			children.add(edge[1]);

			if (!node2root.containsKey(edge[0])) {
				node2root.put(edge[0], edge[0]);
			}
			if (!node2root.containsKey(edge[1])) {
				node2root.put(edge[1], edge[1]);
			}

			int root0 = getRoot(node2root, edge[0]);
			int root1 = getRoot(node2root, edge[1]);

			if (root0 == root1) {
				return false;
			}

			node2root.put(root1, root0);
		}
		return node2root.keySet().stream().mapToInt(node -> getRoot(node2root, node)).distinct().count() == 1;
	}

	int getRoot(Map<Integer, Integer> node2root, int node) {
		int root = node;
		while (node2root.get(root) != root) {
			root = node2root.get(root);
		}

		int tempNode = node;
		while (tempNode != root) {
			int nextTempNode = node2root.get(tempNode);
			node2root.put(tempNode, root);
			tempNode = nextTempNode;
		}

		return root;
	}
}
