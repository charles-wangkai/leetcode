import java.util.ArrayList;
import java.util.List;

class Solution {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> paths = new ArrayList<>();
		search(paths, graph, 0, new ArrayList<>());

		return paths;
	}

	void search(List<List<Integer>> paths, int[][] graph, int node, List<Integer> path) {
		path.add(node);

		if (node == graph.length - 1) {
			paths.add(new ArrayList<>(path));
		}

		for (int adj : graph[node]) {
			search(paths, graph, adj, path);
		}

		path.remove(path.size() - 1);
	}
}
