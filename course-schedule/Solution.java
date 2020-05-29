import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		@SuppressWarnings("unchecked")
		Set<Integer>[] fromSets = new Set[numCourses];
		for (int i = 0; i < fromSets.length; ++i) {
			fromSets[i] = new HashSet<>();
		}

		@SuppressWarnings("unchecked")
		Set<Integer>[] toSets = new Set[numCourses];
		for (int i = 0; i < toSets.length; ++i) {
			toSets[i] = new HashSet<>();
		}

		for (int[] prerequisite : prerequisites) {
			int to = prerequisite[0];
			int from = prerequisite[1];

			fromSets[to].add(from);
			toSets[from].add(to);
		}

		boolean[] takens = new boolean[numCourses];
		for (int i = 0; i < numCourses; ++i) {
			if (!takens[i] && fromSets[i].isEmpty()) {
				take(fromSets, toSets, takens, i);
			}
		}

		return Arrays.stream(fromSets).allMatch(Set::isEmpty);
	}

	void take(Set<Integer>[] fromSets, Set<Integer>[] toSets, boolean[] takens, int index) {
		takens[index] = true;
		for (int to : toSets[index]) {
			fromSets[to].remove(index);

			if (!takens[to] && fromSets[to].isEmpty()) {
				take(fromSets, toSets, takens, to);
			}
		}
	}
}