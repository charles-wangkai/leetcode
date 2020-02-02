import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	static final int[] OFFSETS = { -1, 1 };

	public int maxJumps(int[] arr, int d) {
		int[] indices = IntStream.range(0, arr.length).boxed().sorted((i1, i2) -> Integer.compare(arr[i1], arr[i2]))
				.mapToInt(x -> x).toArray();

		int[] visitNums = new int[arr.length];
		Arrays.fill(visitNums, 1);

		for (int index : indices) {
			for (int offset : OFFSETS) {
				int max = arr[index];
				for (int i = 1; i <= d; ++i) {
					int nextIndex = index + offset * i;
					if (nextIndex < 0 || nextIndex >= arr.length) {
						break;
					}

					if (arr[nextIndex] > max) {
						visitNums[nextIndex] = Math.max(visitNums[nextIndex], visitNums[index] + 1);
						max = arr[nextIndex];
					}
				}
			}
		}

		return Arrays.stream(visitNums).max().getAsInt();
	}
}
