public class PalindromePartitioning_II {
	public int minCut(String s) {
		int[] cuts = new int[s.length() + 1];
		for (int i = 0; i < cuts.length; i++) {
			cuts[i] = i - 1;
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; i - j >= 0 && i + j < s.length()
					&& s.charAt(i - j) == s.charAt(i + j); j++) {
				cuts[i + j + 1] = Math.min(cuts[i + j + 1], cuts[i - j] + 1);
			}
			for (int j = 0; i - j - 1 >= 0 && i + j < s.length()
					&& s.charAt(i - j - 1) == s.charAt(i + j); j++) {
				cuts[i + j + 1] = Math
						.min(cuts[i + j + 1], cuts[i - j - 1] + 1);
			}
		}
		return cuts[s.length()];
	}
}
