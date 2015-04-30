import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
	public boolean isIsomorphic(String s, String t) {
		return generateKey(s).equals(generateKey(t));
	}

	String generateKey(String str) {
		Map<Character, Character> translation = new HashMap<Character, Character>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char from = str.charAt(i);
			if (!translation.containsKey(from)) {
				translation.put(from, (char) (translation.size()));
			}
			sb.append(translation.get(from));
		}
		return sb.toString();
	}
}
