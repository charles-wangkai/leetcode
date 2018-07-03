public class Solution {
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		int count1 = 0;
		int count2 = 0;
		int index1 = 0;
		int index2 = 0;

		while (count1 < n1) {
			if (s1.charAt(index1) == s2.charAt(index2)) {
				index2++;
				if (index2 == s2.length()) {
					index2 = 0;
					count2++;
				}
			}

			index1++;
			if (index1 == s1.length()) {
				index1 = 0;
				count1++;
			}
		}

		return count2 / n2;
	}
}
