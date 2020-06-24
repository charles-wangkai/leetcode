public class Solution {
	public int numTrees(int n) {
		int[] treeNums = new int[n + 1];
		treeNums[0] = 1;

		for (int i = 1; i < treeNums.length; ++i) {
			for (int j = 0; j < i; ++j) {
				treeNums[i] += treeNums[j] * treeNums[i - 1 - j];
			}
		}

		return treeNums[n];
	}
}
