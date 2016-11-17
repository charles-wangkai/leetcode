import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StrongPasswordChecker {
	public int strongPasswordChecker(String s) {
		int needUpper = 1;
		int needLower = 1;
		int needDigit = 1;
		for (char ch : s.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				needUpper = 0;
			} else if (Character.isLowerCase(ch)) {
				needLower = 0;
			} else if (Character.isDigit(ch)) {
				needDigit = 0;
			}
		}

		if (s.length() <= 20) {
			int addTarget = Math.max(0, 6 - s.length());
			int toAdd = 0;
			int toReplace = 0;

			for (int left = 0, right = 0; right < s.length(); right++) {
				if (right - left == 2) {
					if (s.charAt(left) == s.charAt(left + 1) && s.charAt(left + 1) == s.charAt(right)) {
						if (toAdd < addTarget) {
							toAdd++;
							left = right;
						} else {
							toReplace++;
							left = right + 1;
						}
					} else {
						left++;
					}
				}
			}

			return Math.max(addTarget + toReplace, needUpper + needLower + needDigit);
		} else {

			@SuppressWarnings("unchecked")
			Map<Integer, Integer>[] len2cntArray = new Map[3];
			for (int i = 0; i < len2cntArray.length; i++) {
				len2cntArray[i] = new HashMap<Integer, Integer>();
			}

			for (int left = 0, right = 0; right <= s.length(); right++) {
				int len = right - left;
				if (right == s.length() || s.charAt(right) != s.charAt(left)) {
					if (len > 2) {
						addMap(len2cntArray[len % 3], len, 1);
					}

					left = right;
				}
			}

			int deleteTarget = s.length() - 20;
			int toDelete = 0;
			int toReplace = 0;

			for (int i = 0; i < 3; i++) {
				for (Entry<Integer, Integer> entry : len2cntArray[i].entrySet()) {
					int len = entry.getKey();
					int cnt = entry.getValue();

					int letterNum = i + 1;
					int dec = Math.min(cnt, (deleteTarget - toDelete) / letterNum);
					toDelete += dec * letterNum;
					cnt -= dec;

					if (i < 2 && len - letterNum > 2) {
						addMap(len2cntArray[2], len - letterNum, dec);
					}

					toReplace += len / 3 * cnt;
				}
			}

			return deleteTarget + Math.max(toReplace, needUpper + needLower + needDigit);
		}
	}

	void addMap(Map<Integer, Integer> map, int key, int delta) {
		map.put(key, map.getOrDefault(key, 0) + delta);
	}
}
