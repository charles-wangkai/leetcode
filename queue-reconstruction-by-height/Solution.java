import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (p1, p2) -> p1[0] != p2[0] ? (p1[0] - p2[0]) : (p1[1] - p2[1]));

		List<Integer> indices = IntStream.range(0, people.length).collect(ArrayList<Integer>::new, List<Integer>::add,
				List<Integer>::addAll);

		int[][] queue = new int[people.length][];
		int prevHeight = -1;
		int count = -1;
		for (int[] person : people) {
			if (person[0] == prevHeight) {
				queue[indices.remove(person[1] - count)] = person;
				count++;
			} else {
				queue[indices.remove(person[1])] = person;
				count = 1;
			}

			prevHeight = person[0];
		}
		return queue;
	}
}
