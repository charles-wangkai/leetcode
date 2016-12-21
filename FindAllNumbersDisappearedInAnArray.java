import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		for (int num : nums) {
			int index = Math.abs(num) - 1;
			nums[index] = -Math.abs(nums[index]);
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				result.add(i + 1);
			}
		}
		return result;
	}
}
