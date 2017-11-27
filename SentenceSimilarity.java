import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SentenceSimilarity {
	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		Set<Pair> pairSet = Arrays.stream(pairs).map(pair -> new Pair(pair[0], pair[1])).collect(Collectors.toSet());
		return words1.length == words2.length && IntStream.range(0, words1.length)
				.allMatch(i -> words1[i].equals(words2[i]) || pairSet.contains(new Pair(words1[i], words2[i]))
						|| pairSet.contains(new Pair(words2[i], words1[i])));
	}
}

class Pair {
	String word1;
	String word2;

	Pair(String word1, String word2) {
		this.word1 = word1;
		this.word2 = word2;
	}

	@Override
	public int hashCode() {
		return word1.hashCode() * word2.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Pair other = (Pair) obj;
		return word1.equals(other.word1) && word2.equals(other.word2);
	}
}