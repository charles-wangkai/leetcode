import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int subarrayBitwiseORs(int[] A) {
		Set<Integer> orResults = new HashSet<>();
		Set<Integer> prevResults = Collections.emptySet();
		for (int element : A) {
			Set<Integer> currResults = new HashSet<>();
			currResults.add(element);
			for (int prevResult : prevResults) {
				currResults.add(prevResult | element);
			}

			orResults.addAll(currResults);
			prevResults = currResults;
		}
		return orResults.size();
	}
}
