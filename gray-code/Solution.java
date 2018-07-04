import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> grayCode(int n) {
		List<Integer> sequence = new ArrayList<Integer>();
		search(sequence, new int[n], 0);
		return sequence;
	}

	void search(List<Integer> sequence, int[] bits, int index) {
		if (index == bits.length) {
			sequence.add(convertToInt(bits));
			return;
		}

		search(sequence, bits, index + 1);
		bits[index] = 1 - bits[index];
		search(sequence, bits, index + 1);
	}

	int convertToInt(int[] bits) {
		int number = 0;
		for (int bit : bits) {
			number = number * 2 + bit;
		}
		return number;
	}
}
