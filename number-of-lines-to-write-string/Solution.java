import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int[] numberOfLines(int[] widths, String S) {
		List<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < S.length(); i++) {
			int width = widths[S.charAt(i) - 'a'];

			if (!lines.isEmpty() && lines.get(lines.size() - 1) + width <= 100) {
				lines.set(lines.size() - 1, lines.get(lines.size() - 1) + width);
			} else {
				lines.add(width);
			}
		}
		return new int[] { lines.size(), lines.get(lines.size() - 1) };
	}
}
