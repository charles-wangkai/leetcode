import java.util.Arrays;

public class Solution {
	public int findNumberOfLIS(int[] nums) {
		int maxLength = 0;
		LengthAndCount[] lcs = new LengthAndCount[nums.length];
		for (int i = 0; i < lcs.length; i++) {
			lcs[i] = new LengthAndCount(1, 1);
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					int length = lcs[j].length + 1;
					if (length > lcs[i].length) {
						lcs[i] = new LengthAndCount(length, lcs[j].count);
					} else if (length == lcs[i].length) {
						lcs[i].count += lcs[j].count;
					}
				}
			}

			maxLength = Math.max(maxLength, lcs[i].length);
		}

		final int finalMaxLength = maxLength;
		return Arrays.stream(lcs).filter(lc -> lc.length == finalMaxLength).mapToInt(lc -> lc.count).sum();
	}
}

class LengthAndCount {
	int length;
	int count;

	LengthAndCount(int length, int count) {
		this.length = length;
		this.count = count;
	}
}