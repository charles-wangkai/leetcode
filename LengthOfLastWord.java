public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		int lastLength = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (lastLength > 0) {
					break;
				}
			} else {
				lastLength++;
			}
		}
		return lastLength;
	}
}
