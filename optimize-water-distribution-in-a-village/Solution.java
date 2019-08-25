import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
	public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < wells.length; i++) {
			edges.add(new Edge(0, i + 1, wells[i]));
		}
		for (int[] pipe : pipes) {
			edges.add(new Edge(pipe[0], pipe[1], pipe[2]));
		}
		Collections.sort(edges, (e1, e2) -> Integer.compare(e1.cost, e2.cost));

		int[] parents = new int[n + 1];
		Arrays.fill(parents, -1);

		int result = 0;
		for (Edge edge : edges) {
			int root1 = findRoot(parents, edge.node1);
			int root2 = findRoot(parents, edge.node2);

			if (root1 != root2) {
				parents[root2] = root1;

				result += edge.cost;
			}
		}

		return result;
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

class Edge {
	int node1;
	int node2;
	int cost;

	Edge(int node1, int node2, int cost) {
		this.node1 = node1;
		this.node2 = node2;
		this.cost = cost;
	}
}