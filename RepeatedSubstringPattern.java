public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern(String str) {
		for (int length = 1; length * 2 <= str.length(); length++) {
			if (isRepeat(str, str.substring(0, length))) {
				return true;
			}
		}
		return false;
	}

	boolean isRepeat(String str, String sub) {
		if (str.length() % sub.length() != 0) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != sub.charAt(i % sub.length())) {
				return false;
			}
		}
		return true;
	}
}
