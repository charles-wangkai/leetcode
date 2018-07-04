import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		Node[] nodes = new Node[N];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node();
		}

		for (int[] edge : edges) {
			nodes[edge[0]].adjacents.add(edge[1]);
			nodes[edge[1]].adjacents.add(edge[0]);
		}

		postOrderDfs(nodes, new HashSet<>(), 0);

		nodes[0].distanceSum = nodes[0].subNodeDistanceSum;
		preOrderDfs(nodes, new HashSet<>(), 0);

		return Arrays.stream(nodes).mapToInt(node -> node.distanceSum).toArray();
	}

	void postOrderDfs(Node[] nodes, Set<Integer> visited, int index) {
		visited.add(index);

		nodes[index].subNodeCount = 1;
		for (int adjacent : nodes[index].adjacents) {
			if (!visited.contains(adjacent)) {
				postOrderDfs(nodes, visited, adjacent);

				nodes[index].subNodeCount += nodes[adjacent].subNodeCount;
				nodes[index].subNodeDistanceSum += nodes[adjacent].subNodeDistanceSum + nodes[adjacent].subNodeCount;
			}
		}
	}

	void preOrderDfs(Node[] nodes, Set<Integer> visited, int index) {
		visited.add(index);

		for (int adjacent : nodes[index].adjacents) {
			if (!visited.contains(adjacent)) {
				nodes[adjacent].distanceSum = nodes[index].distanceSum - nodes[adjacent].subNodeCount
						+ (nodes.length - nodes[adjacent].subNodeCount);

				preOrderDfs(nodes, visited, adjacent);
			}
		}
	}
}

class Node {
	List<Integer> adjacents = new ArrayList<>();
	int subNodeCount;
	int subNodeDistanceSum;
	int distanceSum;
}