import java.util.Arrays;

public class Solution {
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] fWords = Arrays.stream(words).mapToInt(this::f).toArray();

		return Arrays.stream(queries).mapToInt(query -> {
			int fQuery = f(query);

			return (int) Arrays.stream(fWords).filter(fWord -> fQuery < fWord).count();
		}).toArray();
	}

	int f(String s) {
		int minCh = s.chars().min().getAsInt();

		return (int) s.chars().filter(ch -> ch == minCh).count();
	}
}
