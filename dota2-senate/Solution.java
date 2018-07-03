import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public String predictPartyVictory(String senate) {
		Queue<Integer> radiant = new LinkedList<Integer>();
		Queue<Integer> dire = new LinkedList<Integer>();

		int sequence = 0;
		for (int i = 0; i < senate.length(); i++) {
			if (senate.charAt(i) == 'R') {
				radiant.offer(sequence);
			} else {
				dire.offer(sequence);
			}

			sequence++;
		}

		while (!radiant.isEmpty() && !dire.isEmpty()) {
			int radiantHead = radiant.poll();
			int direHead = dire.poll();

			if (radiantHead < direHead) {
				radiant.offer(sequence);
			} else {
				dire.offer(sequence);
			}

			sequence++;
		}

		return dire.isEmpty() ? "Radiant" : "Dire";
	}
}
