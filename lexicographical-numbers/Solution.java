import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<Integer>();
		search(result, n, 0);
		return result;
	}

	static void search(List<Integer> result, int n, int prevNumber) {
		for (int digit = 0; digit < 10; digit++) {
			int number = prevNumber * 10 + digit;
			if (number == 0) {
				continue;
			}
			if (number > n) {
				break;
			}

			result.add(number);
			search(result, n, number);
		}
	}
}
