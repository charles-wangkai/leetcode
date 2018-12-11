import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	public boolean isAlienSorted(String[] words, String order) {
		Map<Character, Character> translation = buildTranslation(order);

		String[] translated = Arrays.stream(words).map(word -> translate(translation, word)).toArray(String[]::new);
		return IntStream.range(0, translated.length - 1).allMatch(i -> translated[i].compareTo(translated[i + 1]) <= 0);
	}

	Map<Character, Character> buildTranslation(String order) {
		Map<Character, Character> translation = new HashMap<>();
		for (int i = 0; i < order.length(); i++) {
			translation.put(order.charAt(i), (char) ('a' + i));
		}
		return translation;
	}

	String translate(Map<Character, Character> translation, String word) {
		return word.chars().boxed().reduce(new StringBuilder(),
				(result, element) -> result.append(translation.get((char) element.intValue())), StringBuilder::append)
				.toString();
	}
}
