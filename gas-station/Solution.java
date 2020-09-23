class Solution {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int total = 0;
		int current = 0;
		int startingIndex = 0;
		for (int i = 0; i < gas.length; ++i) {
			total += gas[i] - cost[i];
			current += gas[i] - cost[i];
			if (current < 0) {
				startingIndex = i + 1;
				current = 0;
			}
		}

		return total >= 0 ? startingIndex : -1;
	}
}
