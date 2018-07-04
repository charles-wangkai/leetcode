public class Solution {
	public char nextGreatestLetter(char[] letters, char target) {
		if (letters[letters.length - 1] <= target) {
			return letters[0];
		}

		int lower = 0;
		int upper = letters.length - 1;
		int index = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (letters[middle] > target) {
				upper = middle - 1;
				index = middle;
			} else {
				lower = middle + 1;
			}
		}
		return letters[index];
	}
}
