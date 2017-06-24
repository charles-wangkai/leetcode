import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumDistanceInArrays {
	public int maxDistance(List<List<Integer>> arrays) {
		List<Integer> mins = arrays.stream().mapToInt(array -> array.get(0)).collect(ArrayList<Integer>::new, List::add,
				List::addAll);
		Collections.sort(mins);

		int result = 0;
		for (List<Integer> array : arrays) {
			result = Math.max(result,
					array.get(array.size() - 1) - ((array.get(0) == mins.get(0)) ? mins.get(1) : mins.get(0)));
		}
		return result;
	}
}
