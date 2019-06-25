public class Solution {
	public void duplicateZeros(int[] arr) {
		int originalLength = 0;
		int duplicatedLength = 0;
		while (duplicatedLength < arr.length) {
			duplicatedLength += (arr[originalLength] == 0) ? 2 : 1;
			originalLength++;
		}

		for (int i = originalLength - 1; i >= 0; i--) {
			if (arr[i] == 0) {
				if (duplicatedLength - 1 < arr.length) {
					arr[duplicatedLength - 1] = 0;
				}
				arr[duplicatedLength - 2] = 0;

				duplicatedLength -= 2;
			} else {
				arr[duplicatedLength - 1] = arr[i];

				duplicatedLength--;
			}
		}
	}
}
