import java.util.Set;

public class WordBreak {
	public boolean wordBreak(String s, Set<String> dict) {
		boolean[] separables = new boolean[s.length() + 1];
		separables[0] = true;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (separables[j] && dict.contains(s.substring(j, i + 1))) {
					separables[i + 1] = true;
					break;
				}
			}
		}
		return separables[s.length()];
	}
}
