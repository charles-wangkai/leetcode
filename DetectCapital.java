public class DetectCapital {
	public boolean detectCapitalUse(String word) {
		return word.equals(word.toUpperCase()) || isLowerCase(word)
				|| (Character.isUpperCase(word.charAt(0)) && isLowerCase(word.substring(1)));
	}

	boolean isLowerCase(String s) {
		return s.equals(s.toLowerCase());
	}
}
