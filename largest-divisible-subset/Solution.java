import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		Arrays.sort(nums);

		int lastIndex = -1;
		Element[] elements = new Element[nums.length];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = new Element(1, -1);

			for (int j = 0; j < i; ++j) {
				if (nums[i] % nums[j] == 0 && elements[j].size + 1 > elements[i].size) {
					elements[i] = new Element(elements[j].size + 1, j);
				}
			}

			if (lastIndex < 0 || elements[i].size > elements[lastIndex].size) {
				lastIndex = i;
			}
		}

		List<Integer> result = new ArrayList<>();
		for (int i = lastIndex; i != -1; i = elements[i].prevIndex) {
			result.add(nums[i]);
		}

		return result;
	}
}

class Element {
	int size;
	int prevIndex;

	Element(int size, int prevIndex) {
		this.size = size;
		this.prevIndex = prevIndex;
	}
}