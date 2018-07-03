import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int distributeCandies(int[] candies) {
		return Math.min(candies.length / 2,
				Arrays.stream(candies).collect(HashSet<Integer>::new, Set::add, Set::addAll).size());
	}
}
