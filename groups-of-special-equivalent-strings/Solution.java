import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public int numSpecialEquivGroups(String[] A) {
		return Arrays.stream(A).map(this::computeSign).collect(Collectors.toSet()).size();
	}

	String computeSign(String s) {
		return collect(s, 0) + "+" + collect(s, 1);
	}

	String collect(String s, int beginIndex) {
		List<Character> letters = new ArrayList<>();
		for (int i = beginIndex; i < s.length(); i += 2) {
			letters.add(s.charAt(i));
		}
		Collections.sort(letters);
		return letters.toString();
	}
}
