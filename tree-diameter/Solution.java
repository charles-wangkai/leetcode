import java.util.ArrayList;
import java.util.List;

public class Solution {
	int diameter;

	public int treeDiameter(int[][] edges) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjLists = new List[edges.length + 1];
		for (int i = 0; i < adjLists.length; i++) {
			adjLists[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			adjLists[edge[0]].add(edge[1]);
			adjLists[edge[1]].add(edge[0]);
		}

		diameter = 0;
		search(adjLists, new boolean[adjLists.length], 0, 0);

		return diameter;
	}

	int search(List<Integer>[] adjLists, boolean[] visited, int node, int parentPathLength) {
		visited[node] = true;

		int maxPathLength = parentPathLength;
		int secondMaxPathLength = 0;

		int maxSubtreePathLength = 0;
		for (int adj : adjLists[node]) {
			if (!visited[adj]) {
				int pathLength = search(adjLists, visited, adj, parentPathLength + 1) + 1;

				if (pathLength > maxPathLength) {
					secondMaxPathLength = maxPathLength;
					maxPathLength = pathLength;
				} else if (pathLength > secondMaxPathLength) {
					secondMaxPathLength = pathLength;
				}

				maxSubtreePathLength = Math.max(maxSubtreePathLength, pathLength);
			}
		}

		diameter = Math.max(diameter, maxPathLength + secondMaxPathLength);

		return maxSubtreePathLength;
	}
}
