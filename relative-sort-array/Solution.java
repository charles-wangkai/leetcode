import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		Map<Integer, Integer> value2ToCount = Arrays.stream(arr2).boxed()
				.collect(Collectors.toMap(Function.identity(), value -> 0));
		List<Integer> value1s = new ArrayList<>();

		for (int value : arr1) {
			if (value2ToCount.containsKey(value)) {
				value2ToCount.put(value, value2ToCount.get(value) + 1);
			} else {
				value1s.add(value);
			}
		}

		List<Integer> result = new ArrayList<>();
		for (int value2 : arr2) {
			for (int i = 0; i < value2ToCount.get(value2); i++) {
				result.add(value2);
			}
		}

		Collections.sort(value1s);
		result.addAll(value1s);

		return result.stream().mapToInt(x -> x).toArray();
	}
}
