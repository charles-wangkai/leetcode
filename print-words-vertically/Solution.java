import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public List<String> printVertically(String s) {
		String[] words = s.split(" ");
		int maxLength = Arrays.stream(words).mapToInt(String::length).max().getAsInt();

		@SuppressWarnings("unchecked")
		List<Character>[] rows = new List[maxLength];
		for (int i = 0; i < rows.length; ++i) {
			rows[i] = new ArrayList<>();
		}

		for (String word : words) {
			for (int i = 0; i < rows.length; ++i) {
				rows[i].add((i < word.length()) ? word.charAt(i) : ' ');
			}
		}

		return Arrays.stream(rows)
				.map(row -> row.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
						.toString().replaceFirst("\\s+$", ""))
				.collect(Collectors.toList());
	}
}
