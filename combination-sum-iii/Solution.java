import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		search(result, 1, k, n, new ArrayList<Integer>());
		return result;
	}

	void search(List<List<Integer>> result, int number, int remainK,
			int remainN, List<Integer> set) {
		if (number == 10) {
			if (remainK == 0 && remainN == 0) {
				result.add(new ArrayList<Integer>(set));
			}
			return;
		}

		search(result, number + 1, remainK, remainN, set);

		set.add(number);
		search(result, number + 1, remainK - 1, remainN - number, set);
		set.remove(set.size() - 1);
	}
}
