import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public int[] gardenNoAdj(int N, int[][] paths) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjacentLists = new List[N];
		for (int i = 0; i < adjacentLists.length; i++) {
			adjacentLists[i] = new ArrayList<>();
		}

		for (int[] path : paths) {
			adjacentLists[path[0] - 1].add(path[1] - 1);
			adjacentLists[path[1] - 1].add(path[0] - 1);
		}

		int[] types = new int[N];
		Arrays.fill(types, -1);
		for (int i = 0; i < types.length; i++) {
			if (types[i] == -1) {
				search(adjacentLists, types, i, 1);
			}
		}
		return types;
	}

	boolean search(List<Integer>[] adjacentLists, int[] types, int index, int type) {
		types[index] = type;

		boolean result = true;
		for (int adjacent : adjacentLists[index]) {
			if (types[adjacent] == types[index]) {
				result = false;

				break;
			} else if (types[adjacent] == -1) {
				for (int i = 1; i <= 4; i++) {
					if (search(adjacentLists, types, adjacent, i)) {
						break;
					}
				}
			}
		}

		if (!result) {
			types[index] = -1;
		}

		return result;
	}
}
