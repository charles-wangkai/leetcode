import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> solutions = new ArrayList<List<Integer>>();
		search(solutions, candidates, target, new ArrayList<Integer>(), 0);
		return solutions;
	}

	void search(List<List<Integer>> solutions, int[] candidates, int remain,
			List<Integer> solution, int index) {
		if (remain == 0) {
			solutions.add(new ArrayList<Integer>(solution));
			return;
		}
		if (index == candidates.length || candidates[index] > remain) {
			return;
		}
		search(solutions, candidates, remain, solution, index + 1);
		if (index > 0 && candidates[index] == candidates[index - 1]) {
			return;
		}
		solution.add(candidates[index]);
		search(solutions, candidates, remain - candidates[index], solution,
				index);
		solution.remove(solution.size() - 1);
	}
}
