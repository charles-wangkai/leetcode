public class CountingBits {
	public int[] countBits(int num) {
		int[] oneNums = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			oneNums[i] = oneNums[i / 2] + i % 2;
		}
		return oneNums;
	}
}
