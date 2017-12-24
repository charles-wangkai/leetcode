import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {
	static final String INITIAL_WHEEL = "0000";

	public int openLock(String[] deadends, String target) {
		if (target.contains(INITIAL_WHEEL)) {
			return 0;
		}

		Set<String> deads = new HashSet<String>(Arrays.asList(deadends));
		if (deads.contains(INITIAL_WHEEL)) {
			return -1;
		}

		Map<String, Integer> wheel2move = new HashMap<String, Integer>();
		wheel2move.put(INITIAL_WHEEL, 0);
		Queue<String> queue = new LinkedList<String>();
		queue.offer(INITIAL_WHEEL);
		while (!queue.isEmpty()) {
			String wheel = queue.poll();

			for (int i = 0; i < 4; i++) {
				for (int offset : new int[] { -1, 1 }) {
					String nextWheel = wheel.substring(0, i) + (wheel.charAt(i) - '0' + offset + 10) % 10
							+ wheel.substring(i + 1);

					if (deads.contains(nextWheel) || wheel2move.containsKey(nextWheel)) {
						continue;
					}

					int nextMove = wheel2move.get(wheel) + 1;
					if (nextWheel.equals(target)) {
						return nextMove;
					}
					wheel2move.put(nextWheel, nextMove);
					queue.offer(nextWheel);
				}
			}
		}
		return -1;
	}
}
