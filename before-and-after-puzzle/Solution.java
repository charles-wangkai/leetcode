import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	public List<String> beforeAndAfterPuzzles(String[] phrases) {
		String[][] wordLists = Arrays.stream(phrases).map(phrase -> phrase.split(" ")).toArray(String[][]::new);

		List<String> puzzles = new ArrayList<>();
		for (int i = 0; i < wordLists.length; i++) {
			for (int j = 0; j < wordLists.length; j++) {
				if (j != i && wordLists[i][wordLists[i].length - 1].equals(wordLists[j][0])) {
					puzzles.add(Stream.concat(Arrays.stream(wordLists[i]), Arrays.stream(wordLists[j]).skip(1))
							.collect(Collectors.joining(" ")));
				}
			}
		}

		return puzzles.stream().distinct().sorted().collect(Collectors.toList());
	}
}
