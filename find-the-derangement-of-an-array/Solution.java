public class Solution {
	final int MODULUS = 1000000007;

	public int findDerangement(int n) {
		int[] derangements = new int[n + 1];
		derangements[0] = 1;
		derangements[1] = 0;

		for (int i = 2; i < derangements.length; i++) {
			derangements[i] = multiplyMod(i - 1, addMod(derangements[i - 1], derangements[i - 2]));
		}

		return derangements[n];
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}
}
