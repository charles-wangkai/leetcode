public class PhoneDirectory {
	boolean[] used;
	int[] stack;
	int topIndex;

	/**
	 * Initialize your data structure here
	 * 
	 * @param maxNumbers
	 *            - The maximum numbers that can be stored in the phone directory.
	 */
	public PhoneDirectory(int maxNumbers) {
		used = new boolean[maxNumbers];
		stack = new int[maxNumbers];
		for (int i = 0; i < maxNumbers; i++) {
			stack[i] = i;
		}
		topIndex = stack.length - 1;
	}

	/**
	 * Provide a number which is not assigned to anyone.
	 * 
	 * @return - Return an available number. Return -1 if none is available.
	 */
	public int get() {
		if (topIndex == -1) {
			return -1;
		} else {
			int number = stack[topIndex];
			topIndex--;
			used[number] = true;
			return number;
		}
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		return !used[number];
	}

	/** Recycle or release a number. */
	public void release(int number) {
		if (used[number]) {
			used[number] = false;
			topIndex++;
			stack[topIndex] = number;
		}
	}
}

// Your PhoneDirectory object will be instantiated and called as such:
// PhoneDirectory obj = new PhoneDirectory(maxNumbers);
// int param_1 = obj.get();
// boolean param_2 = obj.check(number);
// obj.release(number);
