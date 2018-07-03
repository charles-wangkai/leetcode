import java.util.Arrays;

public class Solution {
	public boolean escapeGhosts(int[][] ghosts, int[] target) {
		return Arrays.stream(ghosts)
				.allMatch(ghost -> computeDistance(ghost, target) > computeDistance(new int[] { 0, 0 }, target));
	}

	int computeDistance(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
}
