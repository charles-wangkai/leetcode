public class Solution {
	public int bulbSwitch(int n) {
		return computeSquareRoot(n);
	}

	int computeSquareRoot(int number) {
		int root = (int) Math.round(Math.sqrt(number));
		if (root * root > number) {
			root--;
		}
		return root;
	}
}
