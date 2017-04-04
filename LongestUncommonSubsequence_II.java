import java.util.Arrays;

public class LongestUncommonSubsequence_II {
	public int findLUSlength(String[] strs) {
		int result = -1;
		for (String s : strs) {
			if (Arrays.stream(strs).filter(str -> isSubsequence(s, str)).count() == 1) {
				result = Math.max(result, s.length());
			}
		}
		return result;
	}

	boolean isSubsequence(String s, String target) {
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			while (index < target.length() && target.charAt(index) != s.charAt(i)) {
				index++;
			}

			if (index == target.length()) {
				return false;
			}

			index++;
		}
		return true;
	}
}
