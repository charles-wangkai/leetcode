public class Solution {
	public int findSpecialInteger(int[] arr) {
		int maxCount = 0;
		int valueWithMaxCount = -1;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i != 0 && arr[i] == arr[i - 1]) {
				count++;
			} else {
				count = 1;
			}

			if (count > maxCount) {
				maxCount = count;
				valueWithMaxCount = arr[i];
			}
		}

		return valueWithMaxCount;
	}
}
