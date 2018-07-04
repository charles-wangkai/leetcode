import java.util.Arrays;

public class Solution {
	public int findMinMoves(int[] machines) {
		int total = Arrays.stream(machines).sum();
		if (total % machines.length != 0) {
			return -1;
		}

		int average = total / machines.length;
		int minMove = 0;
		int accumulation = 0;
		for (int machine : machines) {
			int delta = machine - average;
			accumulation += delta;
			minMove = Math.max(minMove, Math.max(Math.abs(accumulation), delta));
		}
		return minMove;
	}
}
