import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] indegrees = new int[numCourses];

		@SuppressWarnings("unchecked")
		List<Integer>[] adjList = new List[numCourses];
		for (int i = 0; i < adjList.length; ++i) {
			adjList[i] = new ArrayList<>();
		}

		for (int[] prerequisite : prerequisites) {
			adjList[prerequisite[1]].add(prerequisite[0]);
			++indegrees[prerequisite[0]];
		}

		Queue<Integer> availableCourses = new LinkedList<>();
		for (int i = 0; i < indegrees.length; ++i) {
			if (indegrees[i] == 0) {
				availableCourses.offer(i);
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!availableCourses.isEmpty()) {
			int taken = availableCourses.poll();
			result.add(taken);

			for (int adj : adjList[taken]) {
				--indegrees[adj];
				if (indegrees[adj] == 0) {
					availableCourses.offer(adj);
				}
			}
		}

		return (result.size() == numCourses) ? result.stream().mapToInt(x -> x).toArray() : new int[0];
	}
}