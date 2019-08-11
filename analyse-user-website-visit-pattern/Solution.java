import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		int[] sortedIndices = IntStream.range(0, username.length).boxed()
				.sorted((i1, i2) -> Integer.compare(timestamp[i1], timestamp[i2])).mapToInt(x -> x).toArray();

		Map<String, List<String>> usernameToWebsites = new HashMap<>();
		for (int i : sortedIndices) {
			if (!usernameToWebsites.containsKey(username[i])) {
				usernameToWebsites.put(username[i], new ArrayList<>());
			}

			usernameToWebsites.get(username[i]).add(website[i]);
		}

		Map<String, Set<List<String>>> usernameToSequences = new HashMap<>();
		for (String user : usernameToWebsites.keySet()) {
			List<String> websites = usernameToWebsites.get(user);

			Set<List<String>> sequences = new HashSet<>();
			for (int i = 0; i < websites.size(); i++) {
				for (int j = i + 1; j < websites.size(); j++) {
					for (int k = j + 1; k < websites.size(); k++) {
						sequences.add(Arrays.asList(websites.get(i), websites.get(j), websites.get(k)));
					}
				}
			}

			usernameToSequences.put(user, sequences);
		}

		Set<List<String>> allSequences = usernameToSequences.values().stream().collect(HashSet::new, Set::addAll,
				Set::addAll);

		Map<List<String>, Integer> sequenceToCount = new HashMap<>();
		for (List<String> sequence : allSequences) {
			sequenceToCount.put(sequence, (int) usernameToSequences.values().stream()
					.filter(sequences -> sequences.contains(sequence)).count());
		}

		int maxCount = sequenceToCount.values().stream().mapToInt(x -> x).max().getAsInt();

		return sequenceToCount.entrySet().stream().filter(entry -> entry.getValue() == maxCount).map(Entry::getKey)
				.min((seq1, seq2) -> {
					int index = 0;
					while (seq1.get(index).equals(seq2.get(index))) {
						index++;
					}

					return seq1.get(index).compareTo(seq2.get(index));
				}).get();
	}
}
