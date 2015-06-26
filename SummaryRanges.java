import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
	public List<String> summaryRanges(int[] nums) {
		List<String> summary = new ArrayList<String>();
		int begin = 0;
		Integer end = null;
		for (int i = 0; i <= nums.length; i++) {
			if (i == nums.length || end == null || nums[i] != end + 1) {
				if (end != null) {
					if (begin == end) {
						summary.add(begin + "");
					} else {
						summary.add(begin + "->" + end);
					}
				}

				if (i != nums.length) {
					begin = nums[i];
					end = nums[i];
				}
			} else {
				end = nums[i];
			}
		}
		return summary;
	}
}
