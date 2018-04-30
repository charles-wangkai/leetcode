import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GoatLatin {
	public String toGoatLatin(String S) {
		String[] words = S.split(" ");
		return IntStream.range(0, words.length).mapToObj(i -> convert(words[i], i)).collect(Collectors.joining(" "));
	}

	String convert(String word, int index) {
		StringBuilder result = new StringBuilder();

		if (isVowel(word.charAt(0))) {
			result.append(word);
		} else {
			result.append(word.substring(1));
			result.append(word.charAt(0));
		}

		result.append("ma");
		for (int i = 0; i < index + 1; i++) {
			result.append("a");
		}
		return result.toString();
	}

	boolean isVowel(char ch) {
		return "aeiou".indexOf(Character.toLowerCase(ch)) >= 0;
	}
}
