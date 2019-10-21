import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
		sortSlots(slots1);
		sortSlots(slots2);

		int index1 = 0;
		int index2 = 0;
		while (index1 != slots1.length && index2 != slots2.length) {
			int[] slot1 = slots1[index1];
			int[] slot2 = slots2[index2];

			int beginTime = Math.max(slot1[0], slot2[0]);
			int endTime = Math.min(slot1[1], slot2[1]);
			if (endTime - beginTime >= duration) {
				return Arrays.asList(beginTime, beginTime + duration);
			}

			if (slot1[0] < slot2[0] || (slot1[0] == slot2[0] && slot1[1] <= slot2[1])) {
				index1++;
			} else {
				index2++;
			}
		}

		return Collections.emptyList();
	}

	void sortSlots(int[][] slots) {
		Arrays.sort(slots, (slot1, slot2) -> Integer.compare(slot1[0], slot2[0]));
	}
}
