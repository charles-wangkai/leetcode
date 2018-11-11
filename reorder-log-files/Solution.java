import java.util.Arrays;

public class Solution {
	public String[] reorderLogFiles(String[] logs) {
		return Arrays.stream(logs).sorted((log1, log2) -> {
			int spaceIndex1 = log1.indexOf(' ');
			String id1 = log1.substring(0, spaceIndex1);
			String content1 = log1.substring(spaceIndex1 + 1);
			boolean isLetterLog1 = Character.isLetter(content1.charAt(0));

			int spaceIndex2 = log2.indexOf(' ');
			String id2 = log2.substring(0, spaceIndex2);
			String content2 = log2.substring(spaceIndex2 + 1);
			boolean isLetterLog2 = Character.isLetter(content2.charAt(0));

			if (isLetterLog1) {
				if (isLetterLog2) {
					int cmp = content1.compareTo(content2);
					if (cmp != 0) {
						return cmp;
					} else {
						return id1.compareTo(id2);
					}
				} else {
					return -1;
				}
			} else {
				if (isLetterLog2) {
					return 1;
				} else {
					return 0;
				}
			}
		}).toArray(String[]::new);
	}
}
