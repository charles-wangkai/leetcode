import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
	public List<List<Integer>> subsets(int[] S) {
		Arrays.sort(S);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		search(result, S, 0, new ArrayList<Integer>());
		return result;
	}

	void search(List<List<Integer>> result, int[] S, int index,
			List<Integer> subset) {
		if (index == S.length) {
			result.add(new ArrayList<Integer>(subset));
			return;
		}
		search(result, S, index + 1, subset);
		subset.add(S[index]);
		search(result, S, index + 1, subset);
		subset.remove(subset.size() - 1);
	}
}
