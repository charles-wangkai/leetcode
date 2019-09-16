
// https://www.geeksforgeeks.org/bridge-in-a-graph/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	int time;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjLists = new List[n];
		for (int i = 0; i < adjLists.length; i++) {
			adjLists[i] = new ArrayList<>();
		}

		for (List<Integer> connection : connections) {
			adjLists[connection.get(0)].add(connection.get(1));
			adjLists[connection.get(1)].add(connection.get(0));
		}

		List<List<Integer>> bridgeEdges = new ArrayList<>();
		time = 0;
		search(bridgeEdges, adjLists, new boolean[n], new int[n], new int[n], new int[n], 0);

		return bridgeEdges;
	}

	void search(List<List<Integer>> bridgeEdges, List<Integer>[] adjLists, boolean[] visited, int[] times, int[] lows,
			int[] parents, int node) {
		visited[node] = true;

		times[node] = time;
		lows[node] = time;
		time++;

		for (int adj : adjLists[node]) {
			if (!visited[adj]) {
				parents[adj] = node;
				search(bridgeEdges, adjLists, visited, times, lows, parents, adj);

				lows[node] = Math.min(lows[node], lows[adj]);

				if (lows[adj] > times[node]) {
					bridgeEdges.add(Arrays.asList(node, adj));
				}
			} else if (adj != parents[node]) {
				lows[node] = Math.min(lows[node], times[adj]);
			}
		}
	}
}
