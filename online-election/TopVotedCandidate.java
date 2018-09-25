import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TopVotedCandidate {
	int[] times;
	int[] leadingPersons;

	public TopVotedCandidate(int[] persons, int[] times) {
		this.times = times;

		int maxVote = 0;
		Map<Integer, Integer> personToVote = new HashMap<>();
		leadingPersons = new int[persons.length];
		for (int i = 0; i < leadingPersons.length; i++) {
			personToVote.put(persons[i], personToVote.getOrDefault(persons[i], 0) + 1);

			if (personToVote.get(persons[i]) >= maxVote) {
				leadingPersons[i] = persons[i];

				maxVote = personToVote.get(persons[i]);
			} else {
				leadingPersons[i] = leadingPersons[i - 1];
			}
		}
	}

	public int q(int t) {
		int index = Arrays.binarySearch(times, t);
		if (index < 0) {
			index = -1 - index - 1;
		}

		return leadingPersons[index];
	}
}

// Your TopVotedCandidate object will be instantiated and called as such:
// TopVotedCandidate obj = new TopVotedCandidate(persons, times);
// int param_1 = obj.q(t);
