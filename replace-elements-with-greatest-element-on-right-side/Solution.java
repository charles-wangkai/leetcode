public class Solution {
	public int[] replaceElements(int[] arr) {
		int max = -1;
		for (int i = arr.length - 1; i >= 0; --i) {
			int nextMax = Math.max(max, arr[i]);
			arr[i] = max;
			max = nextMax;
		}

		return arr;
	}
}
