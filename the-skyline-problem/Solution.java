import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public List<int[]> getSkyline(int[][] buildings) {
		List<Corner> corners = new ArrayList<Corner>();
		for (int[] building : buildings) {
			corners.add(new Corner(building[0], building[2], true));
			corners.add(new Corner(building[1], building[2], false));
		}

		Collections.sort(corners, (corner1, corner2) -> corner1.x - corner2.x);

		SortedMap<Integer, Integer> height2count = new TreeMap<Integer, Integer>();
		height2count.put(0, 1);
		LinkedList<int[]> skyline = new LinkedList<int[]>();
		for (Corner corner : corners) {
			if (corner.leftOrRight) {
				if (!height2count.containsKey(corner.y)) {
					height2count.put(corner.y, 0);
				}
				height2count.put(corner.y, height2count.get(corner.y) + 1);
			} else {
				height2count.put(corner.y, height2count.get(corner.y) - 1);
				if (height2count.get(corner.y) == 0) {
					height2count.remove(corner.y);
				}
			}

			if (!skyline.isEmpty() && corner.x == skyline.getLast()[0]) {
				skyline.removeLast();
			}
			int next_height = height2count.lastKey();
			if (skyline.isEmpty() || next_height != skyline.getLast()[1]) {
				skyline.add(new int[] { corner.x, next_height });
			}
		}

		return skyline;
	}
}

class Corner {
	int x;
	int y;
	boolean leftOrRight;

	Corner(int x, int y, boolean leftOrRight) {
		this.x = x;
		this.y = y;
		this.leftOrRight = leftOrRight;
	}
}