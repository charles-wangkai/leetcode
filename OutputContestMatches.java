import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputContestMatches {
	public String findContestMatch(int n) {
		List<Integer> ranks = Arrays.asList(1);
		for (int i = 1; i < n; i *= 2) {
			List<Integer> nextRanks = new ArrayList<Integer>();

			for (int rank : ranks) {
				nextRanks.add(rank);
				nextRanks.add(i * 2 + 1 - rank);
			}

			ranks = nextRanks;
		}

		return represent(ranks);
	}

	String represent(List<Integer> ranks) {
		int size = ranks.size();

		if (size == 1) {
			return String.valueOf(ranks.get(0));
		} else {
			return String.format("(%s,%s)", represent(ranks.subList(0, size / 2)),
					represent(ranks.subList(size / 2, size)));
		}
	}
}
