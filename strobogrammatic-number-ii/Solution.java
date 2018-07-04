import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> findStrobogrammatic(int n) {
		return searchStrobogrammatic(n, false);
	}

	public List<String> searchStrobogrammatic(int n, boolean allowLeadingZero) {
		List<String> result = new ArrayList<String>();
		if (n == 0) {
			result.add("");
		} else if (n == 1) {
			result.add("0");
			result.add("1");
			result.add("8");
		} else {
			List<String> subResult = searchStrobogrammatic(n - 2, true);
			for (String oneSubResult : subResult) {
				if (allowLeadingZero) {
					result.add("0" + oneSubResult + "0");
				}
				result.add("1" + oneSubResult + "1");
				result.add("8" + oneSubResult + "8");
				result.add("6" + oneSubResult + "9");
				result.add("9" + oneSubResult + "6");
			}
		}
		return result;
	}
}
