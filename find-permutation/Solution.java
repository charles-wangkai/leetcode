class Solution {
	public int[] findPermutation(String s) {
		int[] result = new int[s.length() + 1];
		int index = 0;

		int startNumber = 1;
		int countD = 0;
		for (int i = 0; i <= s.length(); ++i) {
			if (i != s.length() && s.charAt(i) == 'D') {
				++countD;
			} else {
				for (int j = startNumber + countD; j >= startNumber; --j) {
					result[index] = j;
					++index;
				}

				startNumber += countD + 1;
				countD = 0;
			}
		}

		return result;
	}
}
