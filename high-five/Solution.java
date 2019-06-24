import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public int[][] highFive(int[][] items) {
		SortedMap<Integer, List<Integer>> idToScores = new TreeMap<>();
		for (int[] item : items) {
			int id = item[0];
			int score = item[1];

			if (!idToScores.containsKey(id)) {
				idToScores.put(id, new ArrayList<>());
			}

			idToScores.get(id).add(score);
		}

		int[][] result = new int[idToScores.size()][2];
		int index = 0;
		for (int id : idToScores.keySet()) {
			result[index][0] = id;
			result[index][1] = computeTopFiveAvg(idToScores.get(id));

			index++;
		}
		return result;
	}

	static int computeTopFiveAvg(List<Integer> scores) {
		return scores.stream().sorted(Collections.reverseOrder()).limit(5).mapToInt(x -> x).sum() / 5;
	}
}
