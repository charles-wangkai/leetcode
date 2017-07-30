public class P_4KeysKeyboard {
	public int maxA(int N) {
		int[] maxNums = new int[N + 1];
		maxNums[1] = 1;
		for (int i = 1; i < maxNums.length; i++) {
			if (i + 1 < maxNums.length) {
				maxNums[i + 1] = Math.max(maxNums[i + 1], maxNums[i] + 1);
			}

			for (int j = i + 3; j < maxNums.length; j++) {
				maxNums[j] = Math.max(maxNums[j], maxNums[i] * (j - i - 1));
			}
		}
		return maxNums[N];
	}
}
