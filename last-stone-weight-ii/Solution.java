import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	public int lastStoneWeightII(int[] stones) {
		int total = Arrays.stream(stones).sum();

		Set<Integer> sums = Stream.of(0).collect(Collectors.toSet());
		for (int stone : stones) {
			Set<Integer> addedSums = sums.stream().map(sum -> sum + stone).collect(Collectors.toSet());
			sums = Stream.concat(sums.stream(), addedSums.stream()).collect(Collectors.toSet());
		}

		return sums.stream().map(sum -> Math.abs(total - sum * 2)).mapToInt(x -> x).min().getAsInt();
	}
}
