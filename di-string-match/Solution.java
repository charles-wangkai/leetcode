public class Solution {
	public int[] diStringMatch(String S) {
		int[] result = new int[S.length() + 1];
		int index = 0;

		int begin = 0;
		int end = 0;
		for (int i = 0; i <= S.length(); i++) {
			if (i < S.length() && S.charAt(i) == 'D') {
				end++;
			} else {
				for (int j = end; j >= begin; j--) {
					result[index] = j;
					index++;
				}

				end++;
				begin = end;
			}
		}

		return result;
	}
}
