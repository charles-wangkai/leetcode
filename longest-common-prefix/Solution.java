public class Solution {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		for (int length = 1;; length++) {
			Character ch = null;
			for (String str : strs) {
				if (length > str.length()
						|| (ch != null && ch != str.charAt(length - 1))) {
					return strs[0].substring(0, length - 1);
				}
				ch = str.charAt(length - 1);
			}
		}
	}
}
