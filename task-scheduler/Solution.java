public class Solution {
	public int leastInterval(char[] tasks, int n) {
		int[] counts = new int[26];
		for (char task : tasks) {
			counts[task - 'A']++;
		}

		int[] waits = new int[counts.length];

		int interval = 0;
		int remain = tasks.length;
		while (remain != 0) {
			int chosen = -1;
			for (int i = 0; i < counts.length; i++) {
				if (counts[i] > 0 && waits[i] == 0 && (chosen < 0 || counts[i] > counts[chosen])) {
					chosen = i;
				}
			}

			for (int i = 0; i < waits.length; i++) {
				if (waits[i] != 0) {
					waits[i]--;
				}
			}

			if (chosen >= 0) {
				counts[chosen]--;
				waits[chosen] = n;
				remain--;
			}

			interval++;
		}
		return interval;
	}
}
