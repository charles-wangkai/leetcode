import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		sort(arr);

		int minDiff = IntStream.range(0, arr.length - 1).map(i -> arr[i + 1] - arr[i]).min().getAsInt();

		return IntStream.range(0, arr.length - 1).filter(i -> arr[i + 1] - arr[i] == minDiff)
				.mapToObj(i -> Arrays.asList(arr[i], arr[i + 1])).collect(Collectors.toList());
	}

	static void sort(int[] arr) {
		System.arraycopy(Arrays.stream(arr).boxed().sorted().mapToInt(x -> x).toArray(), 0, arr, 0, arr.length);
	}
}
