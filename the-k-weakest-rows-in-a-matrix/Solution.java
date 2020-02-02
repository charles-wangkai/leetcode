import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int[] kWeakestRows(int[][] mat, int k) {
		int[] oneCounts = Arrays.stream(mat).mapToInt(row -> (int) Arrays.stream(row).filter(x -> x == 1).count())
				.toArray();

		return IntStream.range(0, mat.length).boxed()
				.sorted((i1, i2) -> (oneCounts[i1] != oneCounts[i2]) ? Integer.compare(oneCounts[i1], oneCounts[i2])
						: Integer.compare(i1, i2))
				.limit(k).mapToInt(x -> x).toArray();
	}
}
