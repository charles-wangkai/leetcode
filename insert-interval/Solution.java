import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> inserted = new ArrayList<>();
		boolean used = false;
		for (int i = 0; i < intervals.length || !used; ++i) {
			int[] selected;
			if (i < intervals.length && (used || intervals[i][0] < newInterval[0])) {
				selected = intervals[i];
			} else {
				selected = newInterval;
				used = true;
				--i;
			}

			if (inserted.isEmpty() || inserted.get(inserted.size() - 1)[1] < selected[0]) {
				inserted.add(selected);
			} else {
				int[] last = inserted.get(inserted.size() - 1);
				last[1] = Math.max(last[1], selected[1]);
			}
		}

		return inserted.toArray(new int[0][]);
	}
}
