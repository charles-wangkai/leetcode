import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
		int groupNum = m;
		for (int i = 0; i < n; i++) {
			if (group[i] == -1) {
				group[i] = groupNum;
				groupNum++;
			}
		}

		Graph groupGraph = new Graph();
		for (int g : group) {
			groupGraph.addNode(g);
		}

		Map<Integer, Graph> groupToItemGraph = new HashMap<>();
		for (int g : groupGraph.nodeToAdjacents.keySet()) {
			groupToItemGraph.put(g, new Graph());
		}
		for (int i = 0; i < n; i++) {
			groupToItemGraph.get(group[i]).addNode(i);
		}

		for (int i = 0; i < n; i++) {
			for (int beforeItem : beforeItems.get(i)) {
				if (group[i] == group[beforeItem]) {
					groupToItemGraph.get(group[i]).addEdge(beforeItem, i);
				} else {
					groupGraph.addEdge(group[beforeItem], group[i]);
				}
			}
		}

		List<Integer> sortedGroups = groupGraph.topologicalSort();
		if (sortedGroups == null) {
			return new int[0];
		}

		List<Integer> result = new ArrayList<>();
		for (int g : sortedGroups) {
			List<Integer> sortedItems = groupToItemGraph.get(g).topologicalSort();
			if (sortedItems == null) {
				return new int[0];
			}

			result.addAll(sortedItems);
		}

		return result.stream().mapToInt(x -> x).toArray();
	}
}

class Graph {
	Map<Integer, List<Integer>> nodeToAdjacents = new HashMap<>();

	void addNode(int node) {
		if (!nodeToAdjacents.containsKey(node)) {
			nodeToAdjacents.put(node, new ArrayList<>());
		}
	}

	void addEdge(int from, int to) {
		nodeToAdjacents.get(from).add(to);
	}

	List<Integer> topologicalSort() {
		Map<Integer, Integer> nodeToInDegree = new HashMap<>();
		for (int node : nodeToAdjacents.keySet()) {
			nodeToInDegree.put(node, 0);
		}

		for (List<Integer> adjacents : nodeToAdjacents.values()) {
			for (int adjacent : adjacents) {
				nodeToInDegree.put(adjacent, nodeToInDegree.get(adjacent) + 1);
			}
		}

		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		for (int node : nodeToInDegree.keySet()) {
			if (nodeToInDegree.get(node) == 0) {
				queue.offer(node);
			}
		}

		while (!queue.isEmpty()) {
			int head = queue.poll();

			result.add(head);

			for (int adjacent : nodeToAdjacents.get(head)) {
				nodeToInDegree.put(adjacent, nodeToInDegree.get(adjacent) - 1);

				if (nodeToInDegree.get(adjacent) == 0) {
					queue.offer(adjacent);
				}
			}
		}

		if (result.size() != nodeToInDegree.size()) {
			return null;
		}

		return result;
	}
}