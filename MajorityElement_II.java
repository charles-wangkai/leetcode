import java.util.ArrayList;
import java.util.List;

public class MajorityElement_II {
	public List<Integer> majorityElement(int[] nums) {
		int candidate1 = 0;
		int count1 = 0;
		int candidate2 = 0;
		int count2 = 0;
		for (int num : nums) {
			if (count1 <= 0) {
				if (count2 <= 0 || num != candidate2) {
					candidate1 = num;
					count1 += 2;
					count2--;
				} else {
					count1--;
					count2 += 2;
				}
			} else if (count2 <= 0) {
				if (num == candidate1) {
					count1 += 2;
					count2--;
				} else {
					candidate2 = num;
					count1--;
					count2 += 2;
				}
			} else if (num == candidate1) {
				count1 += 2;
				count2--;
			} else if (num == candidate2) {
				count1--;
				count2 += 2;
			} else {
				count1--;
				count2--;
			}
		}

		List<Integer> majorities = new ArrayList<Integer>();
		if (count1 > 0 && isMajority(nums, candidate1)) {
			majorities.add(candidate1);
		}
		if (count2 > 0 && isMajority(nums, candidate2)) {
			majorities.add(candidate2);
		}
		return majorities;
	}

	boolean isMajority(int[] nums, int candidate) {
		int count = 0;
		for (int num : nums) {
			if (num == candidate) {
				count++;
			}
		}
		return count > nums.length / 3;
	}
}
