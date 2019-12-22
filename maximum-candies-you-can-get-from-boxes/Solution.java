import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
		int n = status.length;
		boolean[] processed = new boolean[n];
		boolean[] found = new boolean[n];
		boolean[] hasKeys = new boolean[n];

		Queue<Integer> queue = new LinkedList<>();
		for (int initialBox : initialBoxes) {
			found[initialBox] = true;
			queue.offer(initialBox);
		}

		int result = 0;
		while (!queue.isEmpty()) {
			int box = queue.poll();
			if (processed[box] || !found[box] || (status[box] == 0 && !hasKeys[box])) {
				continue;
			}

			processed[box] = true;
			result += candies[box];

			for (int key : keys[box]) {
				hasKeys[key] = true;
				queue.offer(key);
			}

			for (int containedBox : containedBoxes[box]) {
				found[containedBox] = true;
				queue.offer(containedBox);
			}
		}

		return result;
	}
}
