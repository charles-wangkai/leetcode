import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DesignSearchAutocompleteSystem {
	Map<String, Integer> sentence2time;
	List<SentenceAndTime> candidates;
	String sentence;

	public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
		sentence2time = new HashMap<String, Integer>();
		for (int i = 0; i < sentences.length; i++) {
			sentence2time.put(sentences[i], times[i]);
		}

		init();
	}

	void init() {
		candidates = sentence2time.keySet().stream()
				.map(sentence -> new SentenceAndTime(sentence, sentence2time.get(sentence)))
				.collect(Collectors.toList());
		Collections.sort(candidates,
				(c1, c2) -> (c1.time != c2.time) ? (c2.time - c1.time) : c1.sentence.compareTo(c2.sentence));

		sentence = "";
	}

	public List<String> input(char c) {
		if (c == '#') {
			sentence2time.put(sentence, sentence2time.getOrDefault(sentence, 0) + 1);

			init();

			return Collections.emptyList();
		} else {
			for (int i = 0; i < candidates.size(); i++) {
				String candidateSentence = candidates.get(i).sentence;
				if (candidateSentence.length() == sentence.length()
						|| candidateSentence.charAt(sentence.length()) != c) {
					candidates.remove(i);
					i--;
				}
			}

			sentence += c;

			List<String> result = new ArrayList<String>();
			for (int i = 0; i < Math.min(3, candidates.size()); i++) {
				result.add(candidates.get(i).sentence);
			}
			return result;
		}
	}
}

class SentenceAndTime {
	String sentence;
	int time;

	SentenceAndTime(String sentence, int time) {
		this.sentence = sentence;
		this.time = time;
	}
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */