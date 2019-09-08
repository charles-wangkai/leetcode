import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
		int[][] allMinDistances = new int[4][];
		for (int target = 1; target < allMinDistances.length; target++) {
			allMinDistances[target] = buildMinDistances(colors, target);
		}

		return Arrays.stream(queries).map(query -> allMinDistances[query[1]][query[0]]).collect(Collectors.toList());
	}

	int[] buildMinDistances(int[] colors, int target) {
		int[] leftDistances = buildLeftDistances(colors, target);
		int[] rightDistances = buildRightDistances(colors, target);

		return IntStream.range(0, colors.length).map(i -> Math.min(leftDistances[i], rightDistances[i]))
				.map(x -> (x == Integer.MAX_VALUE) ? -1 : x).toArray();
	}

	int[] buildLeftDistances(int[] colors, int target) {
		int[] leftDistances = new int[colors.length];
		int leftIndex = -1;
		for (int i = 0; i < leftDistances.length; i++) {
			if (colors[i] == target) {
				leftIndex = i;
			}

			leftDistances[i] = (leftIndex == -1) ? Integer.MAX_VALUE : (i - leftIndex);
		}

		return leftDistances;
	}

	int[] buildRightDistances(int[] colors, int target) {
		int[] rightDistances = new int[colors.length];
		int rightIndex = -1;
		for (int i = rightDistances.length - 1; i >= 0; i--) {
			if (colors[i] == target) {
				rightIndex = i;
			}

			rightDistances[i] = (rightIndex == -1) ? Integer.MAX_VALUE : (rightIndex - i);
		}

		return rightDistances;
	}
}
