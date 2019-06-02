import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjacentLists = new List[n];
		for (int i = 0; i < adjacentLists.length; i++) {
			adjacentLists[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			adjacentLists[edge[0]].add(edge[1]);
		}

		return search(adjacentLists, new HashMap<>(), new boolean[n], destination, source);
	}

	boolean search(List<Integer>[] adjacentLists, Map<Integer, Boolean> cache, boolean[] visited, int destination,
			int current) {
		if (cache.containsKey(current)) {
			return cache.get(current);
		}

		visited[current] = true;

		boolean result;
		if (adjacentLists[current].isEmpty()) {
			result = current == destination;
		} else {
			result = true;
			for (int adjacent : adjacentLists[current]) {
				if (visited[adjacent] || !search(adjacentLists, cache, visited, destination, adjacent)) {
					result = false;
					break;
				}
			}
		}

		visited[current] = false;

		cache.put(current, result);
		return result;
	}
}
