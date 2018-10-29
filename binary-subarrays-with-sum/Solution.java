import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int numSubarraysWithSum(int[] A, int S) {
		List<Integer> zeroNums = new ArrayList<>();
		int zeroNum = 0;
		for (int element : A) {
			if (element == 0) {
				zeroNum++;
			} else {
				zeroNums.add(zeroNum);
				zeroNum = 0;
			}
		}
		zeroNums.add(zeroNum);

		if (S == 0) {
			return zeroNums.stream().mapToInt(x -> x * (x + 1) / 2).sum();
		}

		int result = 0;
		for (int i = 0; i + S < zeroNums.size(); i++) {
			result += (zeroNums.get(i) + 1) * (zeroNums.get(i + S) + 1);
		}
		return result;
	}
}
