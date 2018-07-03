import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		search(result, graph, 0, new ArrayList<Integer>());
		return result;
	}

	void search(List<List<Integer>> result, int[][] graph, int index, List<Integer> path) {
		path.add(index);

		if (index == graph.length - 1) {
			result.add(new ArrayList<Integer>(path));
		}

		for (int adj : graph[index]) {
			search(result, graph, adj, path);
		}

		path.remove(path.size() - 1);
	}
}
