import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
	public boolean canCross(int[] stones) {
		Map<Integer, Set<Integer>> stone2units = new HashMap<Integer, Set<Integer>>();
		for (int stone : stones) {
			stone2units.put(stone, new HashSet<Integer>());
		}
		stone2units.get(0).add(0);

		for (int i = 0; i < stones.length - 1; i++) {
			for (int unit : stone2units.get(stones[i])) {
				for (int delta = -1; delta <= 1; delta++) {
					int nextUnit = unit + delta;

					if (nextUnit <= 0) {
						continue;
					}

					int nextStone = stones[i] + nextUnit;

					if (!stone2units.containsKey(nextStone)) {
						continue;
					}

					stone2units.get(nextStone).add(nextUnit);
				}
			}
		}

		return !stone2units.get(stones[stones.length - 1]).isEmpty();
	}
}
