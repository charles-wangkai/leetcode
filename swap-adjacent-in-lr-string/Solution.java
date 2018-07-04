import java.util.stream.IntStream;

public class Solution {
	public boolean canTransform(String start, String end) {
		return start.length() == end.length() && start.replaceAll("X", "").equals(end.replaceAll("X", ""))
				&& allLte(findIndices(end, 'L'), findIndices(start, 'L'))
				&& allLte(findIndices(start, 'R'), findIndices(end, 'R'));
	}

	int[] findIndices(String s, char ch) {
		return IntStream.range(0, s.length()).filter(i -> s.charAt(i) == ch).toArray();
	}

	boolean allLte(int[] indices1, int[] indices2) {
		return IntStream.range(0, indices1.length).allMatch(i -> indices1[i] <= indices2[i]);
	}
}
