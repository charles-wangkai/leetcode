public class PaintHouse_II {
	public int minCostII(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}

		int colorNum = costs[0].length;
		int[] minCosts = new int[colorNum];
		int[] prevExceptSelfMinCosts = new int[colorNum];

		for (int i = 0; i < costs.length; i++) {
			int[] nextMinCosts = new int[colorNum];
			for (int j = 0; j < nextMinCosts.length; j++) {
				nextMinCosts[j] = prevExceptSelfMinCosts[j] + costs[i][j];
			}
			minCosts = nextMinCosts;

			prevExceptSelfMinCosts = buildExceptSelfMin(minCosts);
		}

		return min(minCosts);
	}

	int min(int[] a) {
		int result = Integer.MAX_VALUE;
		for (int element : a) {
			result = Math.min(result, element);
		}
		return result;
	}

	int[] buildExceptSelfMin(int[] a) {
		int[] leftMins = new int[a.length];
		int currentMin = Integer.MAX_VALUE;
		for (int i = 0; i < leftMins.length; i++) {
			leftMins[i] = currentMin;
			currentMin = Math.min(currentMin, a[i]);
		}

		int[] rightMins = new int[a.length];
		currentMin = Integer.MAX_VALUE;
		for (int i = rightMins.length - 1; i >= 0; i--) {
			rightMins[i] = currentMin;
			currentMin = Math.min(currentMin, a[i]);
		}

		int[] result = new int[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.min(leftMins[i], rightMins[i]);
		}
		return result;
	}
}
