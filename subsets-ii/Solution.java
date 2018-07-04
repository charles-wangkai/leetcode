import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<List<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		search(subsets, num, new ArrayList<Integer>(), 0, Integer.MIN_VALUE);
		return subsets;
	}

	void search(List<List<Integer>> subsets, int[] num, List<Integer> subset,
			int index, int lower) {
		if (index == num.length) {
			subsets.add(new ArrayList<Integer>(subset));
			return;
		}

		search(subsets, num, subset, index + 1, num[index] + 1);
		if (num[index] >= lower) {
			subset.add(num[index]);
			search(subsets, num, subset, index + 1, num[index]);
			subset.remove(subset.size() - 1);
		}
	}
}
