import java.util.Arrays;

public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		return toKey(s).equals(toKey(t));
	}

	String toKey(String str) {
		char[] letters = str.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}
