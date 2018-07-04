import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		search(result, new char[n + n], 0, n, n);
		return result;
	}

	void search(List<String> result, char[] current, int index, int leftRemain,
			int rightRemain) {
		if (leftRemain == 0 && rightRemain == 0) {
			result.add(new String(current));
			return;
		}

		if (leftRemain > 0) {
			current[index] = '(';
			search(result, current, index + 1, leftRemain - 1, rightRemain);
		}
		if (rightRemain > leftRemain) {
			current[index] = ')';
			search(result, current, index + 1, leftRemain, rightRemain - 1);
		}
	}
}
