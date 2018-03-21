import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class MinimumGeneticMutation {
	int minDistance;

	public int minMutation(String start, String end, String[] bank) {
		minDistance = Integer.MAX_VALUE;
		search(start, end, bank, new HashSet<String>());
		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
	}

	void search(String current, String end, String[] bank, Set<String> path) {
		if (current.equals(end)) {
			minDistance = Math.min(minDistance, path.size());
			return;
		}

		for (String next : bank) {
			if (!path.contains(next) && canMutate(current, next)) {
				path.add(next);
				search(next, end, bank, path);
				path.remove(next);
			}
		}
	}

	boolean canMutate(String gene1, String gene2) {
		return gene1.length() == gene2.length()
				&& IntStream.range(0, gene1.length()).filter(i -> gene1.charAt(i) != gene2.charAt(i)).count() == 1;
	}
}
