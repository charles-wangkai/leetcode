import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		search(result, nums, 0, new ArrayList<Integer>());
		return new ArrayList<List<Integer>>(result);
	}

	void search(Set<List<Integer>> result, int[] nums, int index, List<Integer> subsequences) {
		if (index == nums.length) {
			if (subsequences.size() >= 2) {
				result.add(new ArrayList<Integer>(subsequences));
			}
			return;
		}

		search(result, nums, index + 1, subsequences);
		if (subsequences.isEmpty() || nums[index] >= subsequences.get(subsequences.size() - 1)) {
			subsequences.add(nums[index]);
			search(result, nums, index + 1, subsequences);
			subsequences.remove(subsequences.size() - 1);
		}
	}
}
