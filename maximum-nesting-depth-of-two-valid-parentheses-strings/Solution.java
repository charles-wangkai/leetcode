public class Solution {
	public int[] maxDepthAfterSplit(String seq) {
		int[] result = new int[seq.length()];

		int depth = 0;
		for (int i = 0; i < result.length; i++) {
			if (seq.charAt(i) == '(') {
				result[i] = depth % 2;

				depth++;
			} else {
				depth--;

				result[i] = depth % 2;
			}
		}

		return result;
	}
}
