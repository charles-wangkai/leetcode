import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int heightChecker(int[] heights) {
		int[] sortedHeights = Arrays.stream(heights).sorted().toArray();
		return (int) IntStream.range(0, heights.length).filter(i -> heights[i] != sortedHeights[i]).count();
	}
}
