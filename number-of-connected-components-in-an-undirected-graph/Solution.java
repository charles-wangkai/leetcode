import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int countComponents(int n, int[][] edges) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjacentsList = new ArrayList[n];
		for (int i = 0; i < adjacentsList.length; i++) {
			adjacentsList[i] = new ArrayList<Integer>();
		}

		for (int[] edge : edges) {
			adjacentsList[edge[0]].add(edge[1]);
			adjacentsList[edge[1]].add(edge[0]);
		}

		boolean[] visited = new boolean[n];
		int componentNum = 0;
		for (int i = 0; i < adjacentsList.length; i++) {
			if (!visited[i]) {
				search(adjacentsList, visited, i);
				componentNum++;
			}
		}
		return componentNum;
	}

	void search(List<Integer>[] adjacentsList, boolean[] visited, int node) {
		if (visited[node]) {
			return;
		}

		visited[node] = true;
		for (int adjacent : adjacentsList[node]) {
			search(adjacentsList, visited, adjacent);
		}
	}
}
