public class CircularArrayLoop {
	public boolean circularArrayLoop(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				int loopStart = findLoopStart(nums, i);

				if (loopStart >= 0 && isValidLoop(nums, loopStart)) {
					return true;
				}

				mark(nums, i);
			}
		}
		return false;
	}

	int findLoopStart(int[] nums, int start) {
		int slowPointer = start;
		int fastPointer = start;

		while (true) {
			slowPointer = move(slowPointer, nums[slowPointer], nums.length);

			if (nums[slowPointer] == 0) {
				return -1;
			}

			fastPointer = move(fastPointer, nums[fastPointer], nums.length);
			fastPointer = move(fastPointer, nums[fastPointer], nums.length);

			if (slowPointer == fastPointer) {
				return slowPointer;
			}
		}
	}

	int move(int currentIndex, int delta, int length) {
		return ((currentIndex + delta) % length + length) % length;
	}

	boolean isValidLoop(int[] nums, int loopStart) {
		if (nums[loopStart] % nums.length == 0) {
			return false;
		}

		int index = loopStart;
		while (true) {
			index = move(index, nums[index], nums.length);

			if (index == loopStart) {
				break;
			}

			if (Integer.signum(nums[index]) != Integer.signum(nums[loopStart])) {
				return false;
			}
		}
		return true;
	}

	void mark(int[] nums, int start) {
		int index = start;
		while (nums[index] != 0) {
			nums[index] = 0;

			index = move(index, nums[index], nums.length);
		}
	}
}
