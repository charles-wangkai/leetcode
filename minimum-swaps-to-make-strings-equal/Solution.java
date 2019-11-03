public class Solution {
	public int minimumSwap(String s1, String s2) {
		int xyCount = 0;
		int yxCount = 0;
		for (int i = 0; i < s1.length(); i++) {
			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);

			if (ch1 == 'x' && ch2 == 'y') {
				xyCount++;
			} else if (ch1 == 'y' && ch2 == 'x') {
				yxCount++;
			}
		}

		if ((xyCount + yxCount) % 2 != 0) {
			return -1;
		} else {
			return xyCount / 2 + yxCount / 2 + (xyCount % 2 == 0 ? 0 : 2);
		}
	}
}
