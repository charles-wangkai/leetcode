import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public List<Integer> eventualSafeNodes(int[][] graph) {
		int N = graph.length;

		@SuppressWarnings("unchecked")
		List<Integer>[] fromLists = new List[N];
		for (int i = 0; i < fromLists.length; i++) {
			fromLists[i] = new ArrayList<Integer>();
		}

		for (int from = 0; from < graph.length; from++) {
			for (int to : graph[from]) {
				fromLists[to].add(from);
			}
		}

		List<Integer> result = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();

		int[] remains = new int[N];
		for (int i = 0; i < remains.length; i++) {
			remains[i] = graph[i].length;

			if (remains[i] == 0) {
				result.add(i);
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int from : fromLists[node]) {
				remains[from]--;

				if (remains[from] == 0) {
					result.add(from);
					queue.offer(from);
				}
			}
		}

		Collections.sort(result);
		return result;
	}
}
