import java.util.ArrayList;
import java.util.List;

class Solution {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> sets = new ArrayList<>();
		search(sets, 1, k, n, new ArrayList<>());

		return sets;
	}

	void search(List<List<Integer>> sets, int current, int remainK, int remainN, List<Integer> set) {
		if (current == 10) {
			if (remainK == 0 && remainN == 0) {
				sets.add(new ArrayList<>(set));
			}

			return;
		}

		search(sets, current + 1, remainK, remainN, set);

		set.add(current);
		search(sets, current + 1, remainK - 1, remainN - current, set);
		set.remove(set.size() - 1);
	}
}
