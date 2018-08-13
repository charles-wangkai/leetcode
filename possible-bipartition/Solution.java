import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean possibleBipartition(int N, int[][] dislikes) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjacentLists = new List[N];
		for (int i = 0; i < adjacentLists.length; i++) {
			adjacentLists[i] = new ArrayList<>();
		}

		for (int[] dislike : dislikes) {
			int person1 = dislike[0] - 1;
			int person2 = dislike[1] - 1;

			adjacentLists[person1].add(person2);
			adjacentLists[person2].add(person1);
		}

		int[] groups = new int[N];
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] == 0) {
				if (!search(adjacentLists, groups, i, 1)) {
					return false;
				}
			}
		}
		return true;
	}

	boolean search(List<Integer>[] adjacentLists, int[] groups, int person, int expectedGroup) {
		if (groups[person] != 0) {
			return groups[person] == expectedGroup;
		}

		groups[person] = expectedGroup;

		for (int adjacent : adjacentLists[person]) {
			if (!search(adjacentLists, groups, adjacent, 3 - expectedGroup)) {
				return false;
			}
		}
		return true;
	}
}
