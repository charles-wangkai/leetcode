public class VerifyPreorderSequenceInBinarySearchTree {
	public boolean verifyPreorder(int[] preorder) {
		int lower = Integer.MIN_VALUE;
		int stackSize = 0;
		for (int value : preorder) {
			if (value < lower) {
				return false;
			}
			while (stackSize != 0 && value > preorder[stackSize - 1]) {
				lower = preorder[stackSize - 1];
				stackSize--;
			}
			preorder[stackSize] = value;
			stackSize++;
		}
		return true;
	}
}
