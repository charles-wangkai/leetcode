import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int[] movesToStamp(String stamp, String target) {
		List<Integer> indices = new ArrayList<>();
		int remainNum = target.length();
		StringBuilder sb = new StringBuilder(target);
		while (remainNum != 0) {
			boolean found = false;
			for (int i = 0; i <= sb.length() - stamp.length(); i++) {
				int matchNum = match(sb, stamp, i);
				if (matchNum > 0) {
					indices.add(i);
					remainNum -= matchNum;
					found = true;
					break;
				}
			}

			if (!found) {
				return new int[0];
			}
		}

		reverse(indices);
		return indices.stream().mapToInt(x -> x).toArray();
	}

	int match(StringBuilder sb, String stamp, int beginIndex) {
		int matchNum = 0;
		for (int i = 0; i < stamp.length(); i++) {
			char stampCh = stamp.charAt(i);
			char targetCh = sb.charAt(beginIndex + i);

			if (targetCh != '*') {
				if (targetCh != stampCh) {
					return -1;
				} else {
					matchNum++;
				}
			}
		}

		if (matchNum > 0) {
			for (int i = 0; i < stamp.length(); i++) {
				sb.setCharAt(beginIndex + i, '*');
			}
		}

		return matchNum;
	}

	void reverse(List<Integer> list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			int temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}
	}
}
