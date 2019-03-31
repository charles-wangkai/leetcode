import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Boolean> prefixesDivBy5(int[] A) {
		List<Boolean> result = new ArrayList<>();
		int remainder = 0;
		for (int digit : A) {
			remainder = (remainder * 2 + digit) % 5;

			result.add(remainder == 0);
		}
		return result;
	}
}
