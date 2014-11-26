import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
	Map<Pair, Boolean> history = new HashMap<Pair, Boolean>();

	public boolean isScramble(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		}
		int length = s1.length();
		if (length == 1) {
			return false;
		}
		if (!generateKey(s1).equals(generateKey(s2))) {
			return false;
		}
		Pair pair = new Pair(s1, s2);
		if (history.containsKey(pair)) {
			return history.get(pair);
		}
		boolean scramble = false;
		for (int i = 1; i < length; i++) {
			String s1Left = s1.substring(0, i);
			String s1Right = s1.substring(i);
			if ((isScramble(s1Left, s2.substring(0, i)) && isScramble(s1Right,
					s2.substring(i)))
					|| (isScramble(s1Left, s2.substring(length - i)) && isScramble(
							s1Right, s2.substring(0, length - i)))) {
				scramble = true;
				break;
			}
		}
		history.put(pair, scramble);
		return scramble;
	}

	String generateKey(String str) {
		char[] letters = str.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}

class Pair {
	String str1;
	String str2;

	public Pair(String str1, String str2) {
		this.str1 = str1;
		this.str2 = str2;
	}

	@Override
	public int hashCode() {
		return str1.hashCode() * str2.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Pair other = (Pair) obj;
		return str1.equals(other.str1) && str2.equals(other.str2);
	}
}