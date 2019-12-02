import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		int jumboNum = (tomatoSlices - 2 * cheeseSlices) / 2;
		int smallNum = cheeseSlices - jumboNum;

		return (jumboNum >= 0 && smallNum >= 0 && 4 * jumboNum + 2 * smallNum == tomatoSlices)
				? Arrays.asList(jumboNum, smallNum)
				: Collections.emptyList();
	}
}
