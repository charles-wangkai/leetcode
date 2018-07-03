import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			result.add(0);
		}

		NumberAndIndex[] nis = new NumberAndIndex[nums.length];
		for (int i = 0; i < nis.length; i++) {
			nis[i] = new NumberAndIndex(nums[i], i);
		}

		sort(result, nis, 0, nums.length - 1);

		return result;
	}

	void sort(List<Integer> result, NumberAndIndex[] nis, int beginIndex, int endIndex) {
		if (endIndex <= beginIndex) {
			return;
		}

		int middleIndex = (beginIndex + endIndex) / 2;
		sort(result, nis, beginIndex, middleIndex);
		sort(result, nis, middleIndex + 1, endIndex);

		List<NumberAndIndex> merged = new ArrayList<NumberAndIndex>();
		int leftIndex = beginIndex;
		int rightIndex = middleIndex + 1;
		while (leftIndex <= middleIndex || rightIndex <= endIndex) {
			if (leftIndex == middleIndex + 1
					|| (rightIndex != endIndex + 1 && nis[rightIndex].number >= nis[leftIndex].number)) {
				merged.add(nis[rightIndex]);
				rightIndex++;
			} else {
				result.set(nis[leftIndex].index, result.get(nis[leftIndex].index) + (endIndex + 1 - rightIndex));

				merged.add(nis[leftIndex]);
				leftIndex++;
			}
		}

		for (int i = beginIndex; i <= endIndex; i++) {
			nis[i] = merged.get(i - beginIndex);
		}
	}
}

class NumberAndIndex {
	int number;
	int index;

	NumberAndIndex(int number, int index) {
		this.number = number;
		this.index = index;
	}
}
