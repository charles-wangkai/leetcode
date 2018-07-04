public class Solution {
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < nuts.length; i++) {
			int dist = computeDistance(squirrel, nuts[i]) + computeDistance(nuts[i], tree);
			for (int j = 0; j < nuts.length; j++) {
				if (j != i) {
					dist += computeDistance(tree, nuts[j]) * 2;
				}
			}
			minDist = Math.min(minDist, dist);
		}
		return minDist;
	}

	int computeDistance(int[] pos1, int[] pos2) {
		return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
	}
}
