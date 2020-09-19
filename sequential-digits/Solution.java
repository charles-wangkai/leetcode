import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
	static final String DIGITS = "123456789";
	static final int[] SEQUENTIALS = IntStream.range(0, DIGITS.length())
			.flatMap(
					i -> IntStream.range(i + 1, DIGITS.length()).map(j -> Integer.parseInt(DIGITS.substring(i, j + 1))))
			.sorted().toArray();

	public List<Integer> sequentialDigits(int low, int high) {
		return Arrays.stream(SEQUENTIALS).filter(x -> x >= low && x <= high).boxed().collect(Collectors.toList());
	}
}
