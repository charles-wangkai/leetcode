public class JumpGame {
	public boolean canJump(int[] A) {
		int maxIndex = 0;
		for (int i = 0;; i++) {
			if (maxIndex >= A.length - 1) {
				return true;
			}
			if (i == maxIndex + 1) {
				return false;
			}
			maxIndex = Math.max(maxIndex, i + A[i]);
		}
	}
}
