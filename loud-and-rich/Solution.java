import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		Map<Integer, List<Integer>> personToRicherPersons = new HashMap<>();
		for (int[] element : richer) {
			int richerPerson = element[0];
			int person = element[1];

			if (!personToRicherPersons.containsKey(person)) {
				personToRicherPersons.put(person, new ArrayList<>());
			}

			personToRicherPersons.get(person).add(richerPerson);
		}

		return IntStream.range(0, quiet.length)
				.map(i -> search(personToRicherPersons, quiet, new boolean[quiet.length], i)).toArray();
	}

	int search(Map<Integer, List<Integer>> personToRicherPersons, int[] quiet, boolean[] visited, int person) {
		int quietestPerson = person;
		visited[person] = true;
		if (personToRicherPersons.containsKey(person)) {
			for (int richerPerson : personToRicherPersons.get(person)) {
				if (!visited[richerPerson]) {
					int subQuietestPerson = search(personToRicherPersons, quiet, visited, richerPerson);
					if (quiet[subQuietestPerson] < quiet[quietestPerson]) {
						quietestPerson = subQuietestPerson;
					}
				}
			}
		}
		return quietestPerson;
	}
}
