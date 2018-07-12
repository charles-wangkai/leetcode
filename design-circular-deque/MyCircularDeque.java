public class MyCircularDeque {
	private int[] data;
	private int beginIndex;
	private int itemNum;

	/** Initialize your data structure here. Set the size of the deque to be k. */
	public MyCircularDeque(int k) {
		data = new int[k];
		beginIndex = 0;
		itemNum = 0;
	}

	/**
	 * Adds an item at the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertFront(int value) {
		if (isFull()) {
			return false;
		}

		beginIndex = (beginIndex - 1 + data.length) % data.length;
		data[beginIndex] = value;
		itemNum++;

		return true;
	}

	/**
	 * Adds an item at the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertLast(int value) {
		if (isFull()) {
			return false;
		}

		data[(beginIndex + itemNum) % data.length] = value;
		itemNum++;

		return true;
	}

	/**
	 * Deletes an item from the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteFront() {
		if (isEmpty()) {
			return false;
		}

		beginIndex = (beginIndex + 1) % data.length;
		itemNum--;

		return true;
	}

	/**
	 * Deletes an item from the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteLast() {
		if (isEmpty()) {
			return false;
		}

		itemNum--;

		return true;
	}

	/** Get the front item from the deque. */
	public int getFront() {
		return isEmpty() ? -1 : data[beginIndex];
	}

	/** Get the last item from the deque. */
	public int getRear() {
		return isEmpty() ? -1 : data[(beginIndex + itemNum - 1) % data.length];
	}

	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() {
		return itemNum == 0;
	}

	/** Checks whether the circular deque is full or not. */
	public boolean isFull() {
		return itemNum == data.length;
	}
}

// Your MyCircularDeque object will be instantiated and called as such:
// MyCircularDeque obj = new MyCircularDeque(k);
// boolean param_1 = obj.insertFront(value);
// boolean param_2 = obj.insertLast(value);
// boolean param_3 = obj.deleteFront();
// boolean param_4 = obj.deleteLast();
// int param_5 = obj.getFront();
// int param_6 = obj.getRear();
// boolean param_7 = obj.isEmpty();
// boolean param_8 = obj.isFull();
