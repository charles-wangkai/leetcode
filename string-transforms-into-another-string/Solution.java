import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean canConvert(String str1, String str2) {
		if (str2.chars().distinct().count() == 26) {
			return str1.equals(str2);
		}

		Map<Character, Character> conversion = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch1 = str1.charAt(i);
			char ch2 = str2.charAt(i);

			if (conversion.containsKey(ch1)) {
				if (conversion.get(ch1) != ch2) {
					return false;
				}
			} else {
				conversion.put(ch1, ch2);
			}
		}

		return true;
	}
}
