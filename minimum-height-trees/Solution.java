import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		@SuppressWarnings("unchecked")
		Set<Integer>[] adjacentsArray = new HashSet[n];
		for (int i = 0; i < adjacentsArray.length; i++) {
			adjacentsArray[i] = new HashSet<Integer>();
		}
		for (int[] edge : edges) {
			adjacentsArray[edge[0]].add(edge[1]);
			adjacentsArray[edge[1]].add(edge[0]);
		}

		Map<Integer, Set<Integer>> degree2nodes = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < adjacentsArray.length; i++) {
			addInMap(degree2nodes, adjacentsArray[i].size(), i);
		}

		int remainNum = n;
		while (remainNum > 2) {
			Set<Integer> removedNodes = degree2nodes.remove(1);
			for (int removedNode : removedNodes) {
				for (int adjacent : adjacentsArray[removedNode]) {
					int degree = adjacentsArray[adjacent].size();
					degree2nodes.get(degree).remove(adjacent);
					addInMap(degree2nodes, degree - 1, adjacent);

					adjacentsArray[adjacent].remove(removedNode);
				}
				adjacentsArray[removedNode].clear();
			}

			remainNum -= removedNodes.size();
		}

		return new ArrayList<Integer>(degree2nodes.get(remainNum == 1 ? 0 : 1));
	}

	void addInMap(Map<Integer, Set<Integer>> map, int key, int value) {
		if (!map.containsKey(key)) {
			map.put(key, new HashSet<Integer>());
		}
		map.get(key).add(value);
	}
}
