import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		List<Integer> values = buildValues(req_skills, people);

		int[][] minSizes = new int[values.size() + 1][1 << req_skills.length];
		for (int i = 0; i < minSizes.length; i++) {
			Arrays.fill(minSizes[i], Integer.MAX_VALUE);
		}
		minSizes[0][0] = 0;

		for (int i = 0; i < minSizes.length - 1; i++) {
			for (int j = 0; j < minSizes[i].length; j++) {
				minSizes[i + 1][j] = minSizes[i][j];
			}

			for (int j = 0; j < minSizes[i].length; j++) {
				int next = j | values.get(i);

				if (minSizes[i][j] != Integer.MAX_VALUE) {
					minSizes[i + 1][next] = Math.min(minSizes[i + 1][next], minSizes[i][j] + 1);
				}
			}
		}

		List<Integer> result = new ArrayList<>();
		int j = (1 << req_skills.length) - 1;
		for (int i = values.size(); i >= 1; i--) {
			if (minSizes[i - 1][j] != minSizes[i][j]) {
				for (int k = 0;; k++) {
					if ((k | values.get(i - 1)) == j && minSizes[i - 1][k] != Integer.MAX_VALUE
							&& minSizes[i - 1][k] + 1 == minSizes[i][j]) {
						result.add(i - 1);
						j = k;

						break;
					}
				}
			}
		}

		return result.stream().mapToInt(x -> x).toArray();
	}

	List<Integer> buildValues(String[] req_skills, List<List<String>> people) {
		List<Integer> result = new ArrayList<>();
		for (List<String> person : people) {
			Set<String> skillSet = new HashSet<>(person);

			int value = 0;
			for (String reqSkill : req_skills) {
				value <<= 1;

				if (skillSet.contains(reqSkill)) {
					value++;
				}
			}
			result.add(value);
		}
		return result;
	}
}
