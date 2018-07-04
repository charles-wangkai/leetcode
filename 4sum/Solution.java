import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {
	public List<List<Integer>> fourSum(int[] num, int target) {
		Map<Integer, List<Integer[]>> sum2indices = new HashMap<Integer, List<Integer[]>>();
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				int sum = num[i] + num[j];
				if (!sum2indices.containsKey(sum)) {
					sum2indices.put(sum, new ArrayList<Integer[]>());
				}
				sum2indices.get(sum).add(new Integer[] { i, j });
			}
		}

		Set<List<Integer>> solutionSet = new HashSet<List<Integer>>();
		for (Entry<Integer, List<Integer[]>> entry : sum2indices.entrySet()) {
			int sum = entry.getKey();
			List<Integer[]> indices = entry.getValue();
			List<Integer[]> otherIndices = sum2indices.get(target - sum);
			if (otherIndices == null) {
				continue;
			}
			for (Integer[] ind1 : indices) {
				for (Integer[] ind2 : otherIndices) {
					Set<Integer> totalIndices = new HashSet<Integer>();
					totalIndices.addAll(Arrays.asList(ind1));
					totalIndices.addAll(Arrays.asList(ind2));
					if (totalIndices.size() == 4) {
						List<Integer> solution = new ArrayList<Integer>();
						for (int index : totalIndices) {
							solution.add(num[index]);
						}
						Collections.sort(solution);
						solutionSet.add(solution);
					}
				}
			}
		}
		return new ArrayList<List<Integer>>(solutionSet);
	}
}
