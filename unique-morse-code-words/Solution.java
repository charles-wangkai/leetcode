import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
	static final String[] MORSES = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
			".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };

	public int uniqueMorseRepresentations(String[] words) {
		return Arrays.stream(words)
				.map(word -> String.join("",
						word.chars().mapToObj(letter -> MORSES[letter - 'a']).collect(Collectors.toList())))
				.collect(Collectors.toSet()).size();
	}
}
