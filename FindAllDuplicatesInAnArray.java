import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInAnArray {
	public List<Integer> findDuplicates(int[] nums) {
		int n = nums.length;
		for (int num : nums) {
			int original = num;
			while (original < 1) {
				original += n;
			}

			nums[original - 1] -= n;
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 1 - n) {
				result.add(i + 1);
			}
		}
		return result;
	}
}
