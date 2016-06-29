public class RangeAddition {
	public int[] getModifiedArray(int length, int[][] updates) {
		int[] deltas = new int[length];
		for (int[] update : updates) {
			int startIndex = update[0];
			int endIndex = update[1];
			int inc = update[2];

			deltas[startIndex] += inc;
			if (endIndex + 1 < deltas.length) {
				deltas[endIndex + 1] -= inc;
			}
		}

		int[] a = new int[length];
		int value = 0;
		for (int i = 0; i < a.length; i++) {
			value += deltas[i];
			a[i] = value;
		}
		return a;
	}
}
