import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuddyStrings {
	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length()) {
			return false;
		}

		Map<Character, Integer> letterToCountA = buildLetterToCount(A);
		Map<Character, Integer> letterToCountB = buildLetterToCount(B);

		if (!letterToCountA.equals(letterToCountB)) {
			return false;
		}

		if (A.equals(B)) {
			return letterToCountA.values().stream().anyMatch(count -> count >= 2);
		}

		List<Integer> diffIndices = IntStream.range(0, A.length()).filter(i -> A.charAt(i) != B.charAt(i)).boxed()
				.collect(Collectors.toList());
		return diffIndices.size() == 2 && A.charAt(diffIndices.get(0)) == B.charAt(diffIndices.get(1));
	}

	Map<Character, Integer> buildLetterToCount(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}
		return letterToCount;
	}
}
