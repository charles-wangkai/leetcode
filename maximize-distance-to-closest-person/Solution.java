import java.util.stream.IntStream;

public class Solution {
	public int maxDistToClosest(int[] seats) {
		int leftPersonIndex = -1;
		int[] leftDistances = new int[seats.length];
		for (int i = 0; i < leftDistances.length; i++) {
			if (leftPersonIndex < 0) {
				leftDistances[i] = Integer.MAX_VALUE;
			} else {
				leftDistances[i] = i - leftPersonIndex;
			}

			if (seats[i] == 1) {
				leftPersonIndex = i;
			}
		}

		int rightPersonIndex = -1;
		int[] rightDistances = new int[seats.length];
		for (int i = rightDistances.length - 1; i >= 0; i--) {
			if (rightPersonIndex < 0) {
				rightDistances[i] = Integer.MAX_VALUE;
			} else {
				rightDistances[i] = rightPersonIndex - i;
			}

			if (seats[i] == 1) {
				rightPersonIndex = i;
			}
		}

		return IntStream.range(0, seats.length).filter(i -> seats[i] == 0)
				.map(i -> Math.min(leftDistances[i], rightDistances[i])).max().getAsInt();
	}
}
