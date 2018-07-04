import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	public List<String> letterCasePermutation(String S) {
		Set<String> result = new HashSet<String>();
		search(result, S, "");
		return new ArrayList<String>(result);
	}

	void search(Set<String> result, String S, String transformed) {
		if (transformed.length() == S.length()) {
			result.add(transformed);
			return;
		}

		search(result, S, transformed + Character.toLowerCase(S.charAt(transformed.length())));
		search(result, S, transformed + Character.toUpperCase(S.charAt(transformed.length())));
	}
}
