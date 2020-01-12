import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int makeConnected(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1;
		}

		@SuppressWarnings("unchecked")
		List<Integer>[] adjLists = new List[n];
		for (int i = 0; i < adjLists.length; ++i) {
			adjLists[i] = new ArrayList<>();
		}
		for (int[] connection : connections) {
			adjLists[connection[0]].add(connection[1]);
			adjLists[connection[1]].add(connection[0]);
		}

		boolean[] visited = new boolean[n];
		int componentNum = 0;
		for (int i = 0; i < n; ++i) {
			if (!visited[i]) {
				search(adjLists, visited, i);
				++componentNum;
			}
		}

		return componentNum - 1;
	}

	void search(List<Integer>[] adjLists, boolean[] visited, int node) {
		visited[node] = true;

		for (int adj : adjLists[node]) {
			if (!visited[adj]) {
				search(adjLists, visited, adj);
			}
		}
	}
}
